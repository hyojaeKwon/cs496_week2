package com.example.cs496_week2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    //private final List<String> mFragmentTitleList = new ArrayList<>();

    String name;
    String description;
    HashSet<String> language;
    String gitAddr;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    public interface UpdateableFragment {
        public void updateUI(String name, String description, HashSet<String> language, String gitAddr);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    /*@Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }*/

    @Override
    public int getItemPosition(@NonNull Object object) {
        if(object instanceof UserFragment) {
            ((UpdateableFragment) object).updateUI(name, description, language, gitAddr);
        }

        //don't return POSITION_NONE, avoid fragment recreation.
        return super.getItemPosition(object);
    }

    public void update(String _name, String _description, HashSet<String> _language, String _gitAddr) {
        name = _name;
        description = _description;
        language = _language;
        gitAddr = _gitAddr;
        notifyDataSetChanged();
    }

    public void addFrag(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    public void clear() { mFragmentList.clear(); }
}