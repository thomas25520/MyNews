package com.mynews.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mynews.controller.fragment.TabCategoriesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dutru Thomas on 03/05/2019.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<TabCategoriesFragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    // Returns the fragment to display for a particular page.
    @Override
    public Fragment getItem(int position) {
        return TabCategoriesFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(TabCategoriesFragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}