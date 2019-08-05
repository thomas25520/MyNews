package com.mynews.controller.activities;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;

import com.mynews.R;
import com.mynews.callbacks_interfaces.RootSearchCallBack;
import com.mynews.controller.adapter.ViewPagerAdapter;
import com.mynews.controller.fragment.TabCategoriesFragment;
import com.mynews.data.entities.search.SearchResponse;
import com.mynews.utils.SearchCall;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RootSearchCallBack {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureNavigationDrawer();
        configureDrawerLayout();
        setToolbar();
        setViewPagerAndTabs();
        // displayNotificationOfTheDay(); // Only for test
    }

    @Override
    public void onResponse(SearchResponse searchResponse) {
//        Log.i("LOG","test avec this OK");
        Intent intent = new Intent(this, DisplaySearchActivity.class);
        intent.putExtra("searchResponse", searchResponse.toJson()); // put string object converted with json
        startActivity(intent);
    }

    @Override
    public void onFailure() {
//        Log.i("LOG","test avec this FAIL");
    }

    private String getCurrentDateFormatToApi() {
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
        // FIXME: 16/07/2019 A revoir pas besoin de faire une recherche passer par getTopStories
        // Handle Navigation Item Click
        int id = item.getItemId();
        switch (id) {
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

//    public void notification() {
//        // DOC ANDROID
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "test")
//                .setSmallIcon(R.drawable.ic_news_logo)
//                .setContentTitle("Nouvelles informations disponible")
//                .setAutoCancel(true)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        CharSequence name = "test";
//        String description = "test description";
//        int importance = NotificationManager.IMPORTANCE_DEFAULT;
//        NotificationChannel channel = null;
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            channel = new NotificationChannel("test", name, importance);
//            channel.setDescription(description);
//
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//
//        notificationManager.notify(1, builder.build()); // Display notification, Id is unique for each notification
// ------------------------------------------------------------------------------------------------------------------------------
    // STACKOVERFLOW
//        Intent notifyIntent = new Intent(this, MyNotificationReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager = (AlarmManager) getBaseContext().getSystemService(Context.ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
//                5000, pendingIntent);
// ------------------------------------------------------------------------------------------------------------------------------

// DOC ANDROID REPEAT NOTIFICATION AND TIMER
    // Set the alarm to start at approximately 2:00 p.m.
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR, 20); // Not works ? test with : HOUR_OF_DAY
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);

// With setInexactRepeating(), you have to use one of the AlarmManager interval
// constants--in this case, AlarmManager.INTERVAL_DAY.
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, pendingIntent);
//    -------------------------------------------------------------------------------------------------------------------------
//    }


//    private void displayNotificationOfTheDay() {
    // différence entre extends et implements :
    // Extend = hériatage on peut extend qu'une class en java car pas d'héritage multiple
    // Implement = interface, on doit importer et implémenter les méthodes de l'interface (equivalent héritage multiple)

    // ---------------------------------------------------------------------------------------------------------------------

    // TEST new callback passé en paramètre
//        new SearchCall().test(new RootSearchCallBack() {
//            @Override
//            public void onResponse(SearchResponse searchResponse) {
//                Log.i("LOG","test newCallback passé en paramètre OK");
//            }
//
//            @Override
//            public void onFailure() {
//                Log.i("LOG","test newCallback passé en paramètre FAIL");
//            }
//        });
//
    // -----------------------------------------------------------------------------------------------------------------

    // TEST avec this
//        new SearchCall().test(this);
    // Les méthodes onResponse et OnFaillure sont directement importé dans la class MainActivity (this)

    // ------------------------------------------------------------------------------------------------------------

    // TEST callBack crée avant et passé en param
//        final RootSearchCallBack callback = new RootSearchCallBack() {
//            @Override
//            public void onResponse(SearchResponse searchResponse) {
//                Log.i("LOG", "TEST callBack crée avant et passé en param OK");
//            }
//
//            @Override
//            public void onFailure() {
//                Log.i("LOG", "TEST callBack crée avant et passé en param FAIL");
//            }
//        };
//        new SearchCall().test(callback);
    // -------------------------------------------------------------------------------------------------------------
//    }
}