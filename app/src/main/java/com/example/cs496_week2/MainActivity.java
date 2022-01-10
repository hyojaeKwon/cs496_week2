package com.example.cs496_week2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private NonSwipeViewPager viewPager;
    private FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        adapter = new FragmentAdapter(getSupportFragmentManager(), 1);

        //FragmentAdapter에 컬렉션 담기
        adapter.addFragment(new Frag1());
        adapter.addFragment(new Frag2());
        adapter.addFragment(new Frag3());

        //ViewPager Fragment 연결
        viewPager.setAdapter(adapter);

        //ViewPager과 TabLayout 연결
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Tab 1");
        tabLayout.getTabAt(1).setText("Tab 2");
        tabLayout.getTabAt(2).setText("Tab 3");
    }
}