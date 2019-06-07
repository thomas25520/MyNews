package com.mynews.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mynews.R;
import com.mynews.controller.activities.WebViewActivity;
import com.mynews.controller.adapter.RecyclerViewAdapter;
import com.mynews.data.entities.Result;
import com.mynews.utils.CategoriesCall;
import com.mynews.utils.RecyclerViewHolderListener;

import java.util.ArrayList;
import java.util.List;

public class TabCategoriesFragment extends Fragment implements CategoriesCall.Callbacks {

    int categories = 1;
    RecyclerViewAdapter recyclerAdapter;

    public static TabCategoriesFragment newInstance(int categories) {
        TabCategoriesFragment tabCategoriesFragment = new TabCategoriesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("categories", categories);
        tabCategoriesFragment.categories = categories;
        return tabCategoriesFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_categories, container, false);
        initRecycler(view);
        CategoriesCall categoriesCall = new CategoriesCall();

        switch (categories) {
            case 1:
                categoriesCall.topStories(new CategoriesCall.Callbacks() {
                    @Override
                    public void onResponse(List<Result> result) {
                        recyclerAdapter.setList(result);
                    }

                    @Override
                    public void onFailure() {

                    }
                });
                categoriesCall.topStories(this);
                break;
            case 2:
                // TODO: Call "most popular" service
                break;
            case 3:
                // TODO: Call "business" service
                break;
            default:
                break;
        }
        return view;
    }

    private void initRecycler(View view) {
        RecyclerViewHolderListener listener = new RecyclerViewHolderListener() {
            @Override
            public void onItemClicked(RecyclerView.ViewHolder viewHolder, Object item, int pos) {
                Result result = (Result) item;
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("getUrl", result.getUrl()); // Get Url from API to display on webView
                startActivity(intent);
            }
        };

        recyclerAdapter = new RecyclerViewAdapter(new ArrayList<Result>(), listener);
        RecyclerView rv = view.findViewById(R.id.fragment_tab_categories_recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(recyclerAdapter);
    }

    @Override
    public void onResponse(List<Result> result) {
    }

    @Override
    public void onFailure() {
    }
}