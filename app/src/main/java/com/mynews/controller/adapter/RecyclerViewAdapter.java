package com.mynews.controller.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mynews.R;
import com.mynews.data.entities.Result;

import java.util.List;

/**
 * Created by Dutru Thomas on 13/05/2019.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Result> mList;

    public RecyclerViewAdapter(List<Result> list) {
        this.mList = list;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_tab_categories_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Result result = mList.get(position);
        holder.title.setText(result.getTitle());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView picture;
        private TextView categories;
        private TextView date;
        private TextView title;

        private MyViewHolder(final View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.fragment_tab_categories_item_img);
            categories = itemView.findViewById(R.id.fragment_tab_categories_item_categories);
            date = itemView.findViewById(R.id.fragment_tab_categories_item_date);
            title = itemView.findViewById(R.id.fragment_tab_categories_item_title);
        }
    }
}