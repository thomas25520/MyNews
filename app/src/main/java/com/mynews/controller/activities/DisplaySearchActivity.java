package com.mynews.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.mynews.R;
import com.mynews.controller.adapter.RecyclerViewAdapter;
import com.mynews.data.entities.search.Docs;
import com.mynews.data.entities.search.SearchResponse;
import com.mynews.utils.RecyclerViewHolderListener;

import static com.mynews.R.layout.activity_search_result;

/**
 * Created by Dutru Thomas on 20/06/2019.
 */
public class DisplaySearchActivity extends AppCompatActivity {
    RecyclerViewAdapter recyclerAdapter;
    SearchResponse mSearchResponse = new SearchResponse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_search_result);

        ActionBar actionBar = getSupportActionBar(); // system actionBar
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true); // Arrow back btn

        initRecycler();

        Bundle extras = getIntent().getExtras(); // get extra from searchResponse
        if (extras != null)
            mSearchResponse = mSearchResponse.toObject(extras.getString("searchResponse")); // re-create object from json

        recyclerAdapter.setDocsList(mSearchResponse.getDocs());
    }

    private void initRecycler() {
        RecyclerViewHolderListener listener = new RecyclerViewHolderListener() {
            @Override
            public void onItemClicked(RecyclerView.ViewHolder viewHolder, Object item, int pos) {
                Docs docs = (Docs) item;
                Intent intent = new Intent(DisplaySearchActivity.this, WebViewActivity.class);
                intent.putExtra("getUrl", docs.getUrl()); // Get Url from API to display on webView
                startActivity(intent);
            }
        };

        recyclerAdapter = new RecyclerViewAdapter();
        recyclerAdapter.setListener(listener);
        RecyclerView rv = this.findViewById(R.id.activity_search_recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(recyclerAdapter);
    }

    public boolean onOptionsItemSelected(MenuItem item) { // Button in action bar
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}