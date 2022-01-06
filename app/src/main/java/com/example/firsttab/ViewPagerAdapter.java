package com.example.firsttab;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    String technology;
    int status;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    public interface UpdateableFragment {
        public void updateUI(int status, String technology);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ImageFragment.newInstance(status, technology);
        }

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if(object instanceof ImageFragment) {
            ((UpdateableFragment) object).updateUI(status, technology);
        }

        //don't return POSITION_NONE, avoid fragment recreation.
        return super.getItemPosition(object);
    }

    public void update(int _status, String _technology) {
        status = _status;
        technology = _technology;
        notifyDataSetChanged();
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
}