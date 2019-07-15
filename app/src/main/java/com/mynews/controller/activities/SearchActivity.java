package com.mynews.controller.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mynews.R;
import com.mynews.controller.fragment.SearchAndNotificationFragment;

/**
 * Created by Dutru Thomas on 06/05/2019.
 */
public class SearchActivity extends AppCompatActivity { // while implement interface, should implement method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_notification);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true); // active arrow back

        SearchAndNotificationFragment searchAndNotificationFragment = SearchAndNotificationFragment.newInstance("SearchActivity");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_search_notification_frame_layout, searchAndNotificationFragment) // put in the Id of frameLayout the fragment to be must replace
                .commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}