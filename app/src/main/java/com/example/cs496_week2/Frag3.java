package com.example.cs496_week2;

import android.content.Intent;
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
import java.util.HashSet;

public class Frag3 extends Fragment {

    JSONArray user;
    ArrayList<UserInfo> listUser = new ArrayList<>();
    String myId = "khj010909";
    HashSet<String> mySkill = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag3, container, false);

        listUser.clear();
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

                if(Uid.equals(myId)) mySkill = language;
                else listUser.add(new UserInfo(Uid, Uname, Usay, "github.com/"+github, language));
            }
        } catch (IOException e) {

        } catch (JSONException e) {
        }
        Collections.sort(listUser, new Tab3Comparator());

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = v.findViewById(R.id.recycler_matching);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        Tab3RecyclerviewAdapter adapter = new Tab3RecyclerviewAdapter(listUser);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Tab3RecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), DetailProfile.class);
                intent.putExtra("Uid", listUser.get(position).getUid());
                startActivity(intent);
            }
        });

        return v;
    }

    class Tab3Comparator implements Comparator<UserInfo> {

        @Override
        public int compare(UserInfo u1, UserInfo u2) {
            int cnt1 = 0, cnt2 = 0;
            for(String skill: mySkill) {
                if(u1.getLanguage().contains(skill)) cnt1++;
                if(u2.getLanguage().contains(skill)) cnt2++;
            }

            if(cnt1 > cnt2) return 1;
            else if(cnt1 == cnt2 && u1.getUname().compareTo(u2.getUname()) < 0) return 1;
            return -1;
        }
    }
}