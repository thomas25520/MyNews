package com.MyNews.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.MyNews.controller.fragment.TabLayoutCategories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dutru Thomas on 03/05/2019.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<TabLayoutCategories> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    // Returns the fragment to display for a particular page.
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return TabLayoutCategories.newInstance(position + 1);  // mFragmentList.get(position) == TabLayoutCategories
            case 1:
                return TabLayoutCategories.newInstance(position + 1);
            case 2:
                return TabLayoutCategories.newInstance(position + 1);
            default:
                return TabLayoutCategories.newInstance(1);
        }
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(TabLayoutCategories fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}