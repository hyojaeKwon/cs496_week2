package com.example.cs496_week2;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private final HashSet<String> filter = new HashSet<>();
    ArrayList<UserInfo> listUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager assetManager = getResources().getAssets();
        try {
            InputStream inputStream = assetManager.open("users.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            //파일읽기
            String strResult = "";
            String line;
            while((line=reader.readLine()) != null){
                strResult = strResult.concat(line);
            }

            // 가장 큰 JSONObject를 가져옵니다.
            JSONObject jObject = new JSONObject(strResult);
            // 배열을 가져옵니다.
            JSONArray jArray = jObject.getJSONArray("user");

            // Fragment로 넘길 Image Resource
            for(int i = 0; i < jArray.length(); ++i) {
                JSONObject obj = jArray.getJSONObject(i);
                String name = obj.getString("name");
                JSONArray language = obj.getJSONArray("language");
                String des = obj.getString("des");
                String github = obj.getString("github");

                HashSet<String> lang = new HashSet<>();
                for(int j = 0; j < language.length(); ++j)
                    lang.add(language.get(j).toString());
                listUser.add(new UserInfo(name, lang, des, github));
            }
        } catch (IOException e) {

        } catch (JSONException e) {}

        ViewPager viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter fragmentAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        // ViewPager와  FragmentAdapter 연결
        viewPager.setAdapter(fragmentAdapter);

        viewPager.setClipToPadding(false);
        int dpValue = 60;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin/2);

        // FragmentAdapter에 Fragment 추가, Image 개수만큼 추가
        for (int i = 0; i < listUser.size(); i++) {
            UserFragment userFragment = new UserFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", listUser.get(i).getName());
            bundle.putString("description", listUser.get(i).getDescription());
            bundle.putSerializable("language", listUser.get(i).getLanguage());
            bundle.putString("gitAddr", listUser.get(i).getGitAddr());
            userFragment.setArguments(bundle);
            fragmentAdapter.addFrag(userFragment);
        }
        fragmentAdapter.notifyDataSetChanged();

        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        ArrayList<RecyclerviewItem> list = new ArrayList<>();
        list.add(new RecyclerviewItem(R.drawable.python, "Python", 15));
        list.add(new RecyclerviewItem(R.drawable.java, "Java", 12));

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recycler1) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(list) ;
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, String skill_name) {
                if(filter.contains(skill_name)) {
                    filter.remove(skill_name);
                    v.setBackgroundColor(Color.WHITE);
                } else {
                    filter.add(skill_name);
                    v.setBackgroundColor(Color.BLUE);
                }

                fragmentAdapter.clear();
                for (int i = 0; i < listUser.size(); i++) {
                    if(!listUser.get(i).getLanguage().containsAll(filter)) continue;
                    UserFragment userFragment = new UserFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", listUser.get(i).getName());
                    bundle.putString("description", listUser.get(i).getDescription());
                    bundle.putSerializable("language", listUser.get(i).getLanguage());
                    bundle.putString("gitAddr", listUser.get(i).getGitAddr());
                    userFragment.setArguments(bundle);
                    fragmentAdapter.addFrag(userFragment);
                }
                fragmentAdapter.notifyDataSetChanged();
                viewPager.setAdapter(fragmentAdapter);
            }
        }) ;
    }
}