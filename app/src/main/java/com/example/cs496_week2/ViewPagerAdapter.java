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

    String Uid;
    String Uname;
    String Usay;
    String github;
    HashSet<String> language;
    int Utype;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    public interface UpdateableFragment {
        public void updateUI(String Uid, String Uname, String Usay, String github, HashSet<String> language, int Utype);
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
        if (object instanceof UserFragment) {
            ((UpdateableFragment) object).updateUI(Uid, Uname, Usay, github, language, Utype);
        }

        //don't return POSITION_NONE, avoid fragment recreation.
        return super.getItemPosition(object);
    }

    public void update(String _Uid, String _Uname, String _Usay, String _github, HashSet<String> _language, int _Utype) {
        Uid = _Uid;
        Uname = _Uname;
        Usay = _Usay;
        github = _github;
        language = _language;
        Utype = _Utype;
        notifyDataSetChanged();
    }

    public void addFrag(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    public void clear() {
        mFragmentList.clear();
    }
}