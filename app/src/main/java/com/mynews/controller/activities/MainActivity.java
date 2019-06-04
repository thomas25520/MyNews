package com.mynews.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mynews.R;
import com.mynews.controller.Speaker;
import com.mynews.controller.TestClass;
import com.mynews.controller.TestClassCallBack;
import com.mynews.controller.adapter.ViewPagerAdapter;
import com.mynews.controller.fragment.TabCategoriesFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureNavigationView();
        configureDrawerLayout();
        setToolbar();
        setViewPagerAndTabs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Speaker speaker = new Speaker("Thomas");

        speaker.printCallBack(new TestClassCallBack());
        speaker.printCallBack(new TestClass());
//        TestClass thisT = new TestClass();
//        speaker.printCallBack(thisT);
//
//
//        speaker.setClass(thisT, new TestClassCallBack());
//        speaker.sendMsgToAllListener();
    }

    // Configure NavigationView
    private void configureNavigationView() {
        NavigationView navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // Configure Drawer Layout
    private void configureDrawerLayout() {
        mDrawerLayout = findViewById(R.id.activity_main_drawer_layout);
    }

    // Set ViewPagerAndTabs
    private void setViewPagerAndTabs() {
        ViewPager viewPager = findViewById(R.id.activity_main_viewpager);
        addTabs(viewPager);
        TabLayout tabLayout = findViewById(R.id.activity_main_tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setBackgroundColor(getResources().getColor(R.color.colorWhite));
    }

    // Set Toolbar
    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24px);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TabCategoriesFragment(), "TOP STORIES");
        adapter.addFrag(new TabCategoriesFragment(), "MOST POPULAR");
        adapter.addFrag(new TabCategoriesFragment(), "BUSINESS");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_toolbar_menu, menu);
        return true;
    }

    // Selected item on toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.activity_main_toolbar_search_btn:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // select item on Navigation drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle Navigation Item Click
        int id = item.getItemId();
        switch (id) {
            case R.id.activity_main_drawer_arts:
                break;
            case R.id.activity_main_drawer_business:
                break;
            case R.id.activity_main_drawer_entrepreneurs:
                break;
            case R.id.activity_main_drawer_politics:
                break;
            case R.id.activity_main_drawer_sports:
                break;
            case R.id.activity_main_drawer_travels:
                break;
            default:
                break;
        }
        this.mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}