package com.example.cs496_week2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Frag2 extends Fragment {

    JSONArray idea;
    ArrayList<IdeaItem> ideaList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag2, container, false);

        ideaList.clear();
        AssetManager assetManager = getResources().getAssets();
        try {
            InputStream inputStream = assetManager.open("idea.json");
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
            idea = jObject.getJSONArray("idea");

            // Fragment로 넘길 Image Resource
            for (int i = 0; i < idea.length(); ++i) {
                JSONObject obj = idea.getJSONObject(i);
                int Iid = obj.getInt("Iid");
                String Ititle = obj.getString("Ititle");
                String Idescription = obj.getString("Idescription");
                String IauthorId = obj.getString("IauthorId");
                ideaList.add(new IdeaItem(Iid, Ititle, Idescription, IauthorId));
            }
        } catch (IOException e) {

        } catch (JSONException e) {
        }

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = v.findViewById(R.id.recycler_idea);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        IdeaRecyclerviewAdapter adapter = new IdeaRecyclerviewAdapter(ideaList);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = v.findViewById(R.id.tab2_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), AddIdea.class));
            }
        });

        adapter.setOnItemClickListener(new IdeaRecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), DetailIdea.class);
                IdeaItem clickedItem = ideaList.get(position);
                intent.putExtra("Ititle", clickedItem.getItitle());
                intent.putExtra("IauthorId", clickedItem.getIauthorId());
                startActivity(intent);
            }
        });

        return v;
    }
}