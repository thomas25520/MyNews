package com.mynews.controller.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.mynews.R;
import com.mynews.controller.fragment.SearchFragment;

/**
 * Created by Dutru Thomas on 28/06/2019.
 */
public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification); // While fragment no view
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true); // active arrow back

        SearchFragment searchFragment = SearchFragment.newInstance("NotificationActivity");
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_notification, searchFragment).commit();
    }
}

