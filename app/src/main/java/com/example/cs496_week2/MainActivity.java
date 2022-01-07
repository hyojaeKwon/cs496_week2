package com.example.cs496_week2;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private HashSet<String> filter = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fragment로 넘길 Image Resource
        ArrayList<UserInfo> listImage = new ArrayList<>();
        listImage.add(new UserInfo("김덕현", new HashSet<>(Arrays.asList("Python")), "ㅎㅇ", "git1"));
        listImage.add(new UserInfo("권효재", new HashSet<>(Arrays.asList("Java")), "ㅎㅇㅎㅇ", "git2"));

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
        for (int i = 0; i < listImage.size(); i++) {
            ImageFragment imageFragment = new ImageFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", listImage.get(i).getName());
            bundle.putString("description", listImage.get(i).getDescription());
            imageFragment.setArguments(bundle);
            fragmentAdapter.addFrag(imageFragment);
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
                } else {
                    filter.add(skill_name);
                }

                fragmentAdapter.clear();
                for (int i = 0; i < listImage.size(); i++) {
                    if(!listImage.get(i).getLanguage().containsAll(filter)) continue;
                    ImageFragment imageFragment = new ImageFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", listImage.get(i).getName());
                    bundle.putString("description", listImage.get(i).getDescription());
                    imageFragment.setArguments(bundle);
                    fragmentAdapter.addFrag(imageFragment);
                }
                fragmentAdapter.notifyDataSetChanged();
                viewPager.setAdapter(fragmentAdapter);
            }
        }) ;
    }
}