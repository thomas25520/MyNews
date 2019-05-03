package com.MyNews.controller.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.MyNews.R;
import com.MyNews.controller.adapter.ViewPagerAdapter;
import com.MyNews.controller.fragment.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureNavigationView();
        configureDrawerLayout();
        setToolbar();
        setViewPagerAndTabs();
    }

    // Configure NavigationView
    private void configureNavigationView() {
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // Configure Drawer Layout
    private void configureDrawerLayout() {
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // Set ViewPagerAndTabs
    private void setViewPagerAndTabs() {
        ViewPager viewPager = findViewById(R.id.activity_main_viewpager);
        addTabs(viewPager);
        android.support.design.widget.TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    // Set Toolbar
    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TabLayout(), "TOP STORIES");
        adapter.addFrag(new TabLayout(), "MOST POPULAR");
        adapter.addFrag(new TabLayout(), "BUSINESS");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle Navigation Item Click
        int id = item.getItemId();

        switch (id) {
            case R.id.activity_main_drawer_news:
                break;
            case R.id.activity_main_drawer_profile:
                break;
            case R.id.activity_main_drawer_settings:
                break;
            default:
                break;
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}