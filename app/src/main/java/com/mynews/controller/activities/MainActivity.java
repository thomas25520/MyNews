package com.mynews.controller.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mynews.R;
import com.mynews.callbacks_interfaces.SearchResponseCallBack;
import com.mynews.controller.adapter.ViewPagerAdapter;
import com.mynews.controller.fragment.TabCategoriesFragment;
import com.mynews.data.entities.search.SearchResponse;
import com.mynews.utils.NotificationManager;
import com.mynews.utils.SearchCall;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchResponseCallBack {
    private DrawerLayout mDrawerLayout;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureNavigationDrawer();
        configureDrawerLayout();
        setToolbar();
        setViewPagerAndTabs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new notifThread().execute("");
    }

    @Override
    public void onResponse(SearchResponse searchResponse) {
        Intent intent = new Intent(this, DisplaySearchActivity.class);
        intent.putExtra("searchResponse", searchResponse.toJson()); // put string object converted with json
        startActivity(intent);
    }

    private class notifThread extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... vo) {
            NotificationManager notificationManager = new NotificationManager(mContext);
            while (true) {
                notificationManager.displayNotification();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.i("MainActivity", throwable.toString());
    }

    private String getCurrentDateFormatToApi() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(Calendar.getInstance().getTime());
    }

    // Configure NavigationDrawer
    private void configureNavigationDrawer() {
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
        adapter.addFrag(new TabCategoriesFragment(), "AUTOMOBILE");
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
            case R.id.activity_main_toolbar_notification_btn:
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                return true;
            case R.id.activity_main_toolbar_about_btn:
                configureAboutMenu();
                return true;
            case R.id.activity_main_toolbar_help_btn:
                configureHelpMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void configureHelpMenu() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.AlertDialog);
        alertDialogBuilder.setTitle("Help")
                .setMessage("If you need help please contact the developer at the following email address : thomas.dutru@gmail.com")
                .setCancelable(true)
                .setPositiveButton("Quitter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void configureAboutMenu() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.AlertDialog);
        alertDialogBuilder.setTitle("About")
                .setMessage("App version : 1.0\nAndroid mini : 4.4\nCreated by Dutru thomas\nOpenClassrooms's student dev android")
                .setCancelable(true)
                .setPositiveButton("Quitter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    // select item on Navigation drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle Navigation Item Click
        switch (item.getItemId()) {
            case R.id.activity_main_drawer_arts:
                new SearchCall().search(this, "", "arts", getCurrentDateFormatToApi(), getCurrentDateFormatToApi());
                break;
            case R.id.activity_main_drawer_business:
                new SearchCall().search(this, "", "business", getCurrentDateFormatToApi(), getCurrentDateFormatToApi());
                break;
            case R.id.activity_main_drawer_entrepreneurs:
                new SearchCall().search(this, "", "entrepreneurs", getCurrentDateFormatToApi(), getCurrentDateFormatToApi());
                break;
            case R.id.activity_main_drawer_politics:
                new SearchCall().search(this, "", "politics", getCurrentDateFormatToApi(), getCurrentDateFormatToApi());
                break;
            case R.id.activity_main_drawer_sports:
                new SearchCall().search(this, "", "sports", getCurrentDateFormatToApi(), getCurrentDateFormatToApi());
                break;
            case R.id.activity_main_drawer_travels:
                new SearchCall().search(this, "", "travels", getCurrentDateFormatToApi(), getCurrentDateFormatToApi());
                break;
            default:
                break;
        }
        this.mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}