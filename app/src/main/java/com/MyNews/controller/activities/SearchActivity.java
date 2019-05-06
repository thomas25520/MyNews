package com.MyNews.controller.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.MyNews.R;

/**
 * Created by Dutru Thomas on 06/05/2019.
 */
public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setToolbar();
    }

    public void setToolbar() {
        Toolbar mToolbar = findViewById(R.id.activity_search_toolbar);
        mToolbar.setTitle("Search article");
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24px);

        // Return to main activity when click on arrow back button
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}