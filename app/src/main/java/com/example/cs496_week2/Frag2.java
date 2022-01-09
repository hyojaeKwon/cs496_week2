package com.example.cs496_week2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Frag2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag2, container, false);

        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        ArrayList<IdeaItem> list = new ArrayList<>();
        list.add(new IdeaItem("3주차 조 편성", "김덕현", 15));
        list.add(new IdeaItem("3주차 조 편성", "권효재", 12));

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = v.findViewById(R.id.recycler_idea);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        IdeaRecyclerviewAdapter adapter = new IdeaRecyclerviewAdapter(list);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), AddIdea.class));
            }
        });

        adapter.setOnItemClickListener(new IdeaRecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), DetailIdea.class) ;

                // Name 입력 값을 String 값으로 그대로 전달.
                TextView idea_name = (TextView) v.findViewById(R.id.idea_name) ;
                intent.putExtra("idea_name", idea_name.getText().toString()) ;

                // Over20 값을 boolean 값으로 전달.
                TextView person_name = (TextView) v.findViewById(R.id.idea_person_name) ;
                intent.putExtra("person_name", person_name.getText().toString()) ;

                startActivity(intent) ;
                //getActivity().startActivity(new Intent(getActivity(), DetailIdea.class));
            }
        });

        return v;
    }
}