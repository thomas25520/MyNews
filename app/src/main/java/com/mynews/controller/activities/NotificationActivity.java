package com.mynews.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mynews.R;
import com.mynews.controller.fragment.SearchAndNotificationFragment;

/**
 * Created by Dutru Thomas on 28/06/2019.
 */
public class NotificationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_notification); // While fragment no view
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true); // active arrow back

        SearchAndNotificationFragment searchAndNotificationFragment = SearchAndNotificationFragment.newInstance("NotificationActivity");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_search_notification_frame_layout, searchAndNotificationFragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// Action when arrow back button is click
            Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}