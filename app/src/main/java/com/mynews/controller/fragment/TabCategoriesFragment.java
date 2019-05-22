package com.mynews.controller.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mynews.R;
import com.mynews.controller.adapter.RecyclerViewAdapter;

import java.util.Arrays;
import java.util.List;

public class TabCategoriesFragment extends Fragment {

    int categories = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private final List<Pair<String, String>> list1 = Arrays.asList(
            Pair.create("list1", "list1"),
            Pair.create("list1", "list1"),
            Pair.create("list1", "list1"),
            Pair.create("list1", "list1"),
            Pair.create("list1", "list1"),
            Pair.create("list1", "list1"),
            Pair.create("list1", "list1"),
            Pair.create("list1", "list1"),
            Pair.create("list1", "list1"),
            Pair.create("list1", "list1")
    );

    private final List<Pair<String, String>> list2 = Arrays.asList(
            Pair.create("list2", "list2"),
            Pair.create("list2", "list2"),
            Pair.create("list2", "list2"),
            Pair.create("list2", "list2"),
            Pair.create("list2", "list2"),
            Pair.create("list2", "list2"),
            Pair.create("list2", "list2"),
            Pair.create("list2", "list2"),
            Pair.create("list2", "list2"),
            Pair.create("list2", "list2")
    );

    private final List<Pair<String, String>> list3 = Arrays.asList(
            Pair.create("list3", "list3"),
            Pair.create("list3", "list3"),
            Pair.create("list3", "list3"),
            Pair.create("list3", "list3"),
            Pair.create("list3", "list3"),
            Pair.create("list3", "list3"),
            Pair.create("list3", "list3"),
            Pair.create("list3", "list3"),
            Pair.create("list3", "list3"),
            Pair.create("list3", "list3")
    );

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
        RecyclerView.Adapter recyclerViewAdapter = new RecyclerViewAdapter();

        switch (categories) {
            case 1:
                // TODO: Call "top stories" service
                ((RecyclerViewAdapter) recyclerViewAdapter).setList(list1);
                break;
            case 2:
                // TODO: Call "most popular" service
                ((RecyclerViewAdapter) recyclerViewAdapter).setList(list2);
                break;
            case 3:
                // TODO: Call "business" service
                ((RecyclerViewAdapter) recyclerViewAdapter).setList(list3);
                break;
            default:
                ((RecyclerViewAdapter) recyclerViewAdapter).setList(list1);
                break;
        }
        setRecyclerView(view, recyclerViewAdapter);
        return view;
    }

    public void setRecyclerView(View view, RecyclerView.Adapter recyclerViewAdapter) {
        RecyclerView rv = view.findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(recyclerViewAdapter);
    }
}