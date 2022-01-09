package com.example.cs496_week2;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

//외부에서 new Frag1 호출 시
public class Frag1 extends Fragment {

    private final HashSet<String> filter = new HashSet<>();
    ArrayList<UserInfo> listUser = new ArrayList<>();
    HashMap<String, Integer> skillMap = new HashMap<String, Integer>();
    JSONArray user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag1, container, false);

        listUser.clear();
        filter.clear();
        AssetManager assetManager = getResources().getAssets();
        try {
            InputStream inputStream = assetManager.open("user.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            //파일읽기
            String strResult = "";
            String line;
            while ((line = reader.readLine()) != null) {
                strResult = strResult.concat(line);
            }

            // 가장 큰 JSONObject를 가져옵니다.
            JSONObject jObject = new JSONObject(strResult);
            // 배열을 가져옵니다.
            user = jObject.getJSONArray("user");

            skillMap.clear();

            // Fragment로 넘길 Image Resource
            for (int i = 0; i < user.length(); ++i) {
                JSONObject obj = user.getJSONObject(i);
                String Uid = obj.getString("Uid");
                String Uname = obj.getString("Uname");
                String Usay = obj.getString("Usay");
                String github = obj.getString("github");
                String Llang1 = obj.getString("Llang1");
                String Llang2 = obj.getString("Llang2");
                String Llang3 = obj.getString("Llang3");

                HashSet<String> language = new HashSet<>();
                language.add(Llang1);
                language.add(Llang2);
                language.add(Llang3);

                if(skillMap.containsKey(Llang1)) {
                    skillMap.put(Llang1, skillMap.get(Llang1)+1);
                } else {
                    skillMap.put(Llang1, 1);
                }
                if(skillMap.containsKey(Llang2)) {
                    skillMap.put(Llang2, skillMap.get(Llang2)+1);
                } else {
                    skillMap.put(Llang2, 1);
                }
                if(skillMap.containsKey(Llang3)) {
                    skillMap.put(Llang3, skillMap.get(Llang3)+1);
                } else {
                    skillMap.put(Llang3, 1);
                }

                listUser.add(new UserInfo(Uid, Uname, Usay, "github.com/"+github, language));
            }
        } catch (IOException e) {

        } catch (JSONException e) {
        }

        ViewPager viewPager = v.findViewById(R.id.viewPager_profile);
        ViewPagerAdapter fragmentAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        // ViewPager와  FragmentAdapter 연결
        viewPager.setAdapter(fragmentAdapter);

        viewPager.setClipToPadding(false);
        int dpValue = 60;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin / 2);

        // FragmentAdapter에 Fragment 추가, Image 개수만큼 추가
        for (int i = 0; i < listUser.size(); i++) {
            UserFragment userFragment = new UserFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Uid", listUser.get(i).getUid());
            bundle.putString("Uname", listUser.get(i).getUname());
            bundle.putString("Usay", listUser.get(i).getUsay());
            bundle.putString("github", listUser.get(i).getGithub());
            bundle.putSerializable("language", listUser.get(i).getLanguage());
            userFragment.setArguments(bundle);
            fragmentAdapter.addFrag(userFragment);
        }
        fragmentAdapter.notifyDataSetChanged();

        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        ArrayList<SkillItem> list = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: skillMap.entrySet()) {
            list.add(new SkillItem(R.drawable.python, entry.getKey(), entry.getValue()));
        }
        Collections.sort(list, new SkillItemComparator());

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = v.findViewById(R.id.recycler_skill);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        SkillRecyclerviewAdapter adapter = new SkillRecyclerviewAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SkillRecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, String skill_name) {
                if (filter.contains(skill_name)) {
                    filter.remove(skill_name);
                    v.setBackgroundColor(Color.WHITE);
                } else {
                    filter.add(skill_name);
                    v.setBackgroundColor(Color.BLUE);
                }

                fragmentAdapter.clear();
                for (int i = 0; i < listUser.size(); i++) {
                    if (!listUser.get(i).getLanguage().containsAll(filter)) continue;
                    UserFragment userFragment = new UserFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("Uid", listUser.get(i).getUid());
                    bundle.putString("Uname", listUser.get(i).getUname());
                    bundle.putString("Usay", listUser.get(i).getUsay());
                    bundle.putString("github", listUser.get(i).getGithub());
                    bundle.putSerializable("language", listUser.get(i).getLanguage());
                    userFragment.setArguments(bundle);
                    fragmentAdapter.addFrag(userFragment);
                }
                fragmentAdapter.notifyDataSetChanged();
                viewPager.setAdapter(fragmentAdapter);
            }
        });

        return v;
    }

    class SkillItemComparator implements Comparator<SkillItem> {

        @Override
        public int compare(SkillItem s1, SkillItem s2) {
            if(s1.getCount() < s2.getCount()) return 1;
            else if(s1.getCount() == s2.getCount() && s1.getName().compareTo(s2.getName()) > 0) return 1;
            return -1;
        }
    }
}