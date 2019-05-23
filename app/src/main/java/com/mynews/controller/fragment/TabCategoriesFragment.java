package com.mynews.controller.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mynews.R;
import com.mynews.data.entities.Root;
import com.mynews.data.remote.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabCategoriesFragment extends Fragment {

    int categories = 1;

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

        RetrofitManager.getInstance().getTopStories().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
                response.body().getResults();
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });


        switch (categories) {
            case 1:
                // TODO: Call "top stories" service
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
        //  setRecyclerView(view, recyclerViewAdapter);
        return view;
    }

    public void setRecyclerView(View view, RecyclerView.Adapter recyclerViewAdapter) {
        RecyclerView rv = view.findViewById(R.id.fragment_tab_categories_recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(recyclerViewAdapter);
    }
}