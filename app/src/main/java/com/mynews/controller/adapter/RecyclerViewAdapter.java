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
import com.mynews.utils.RecyclerViewHolderListener;

import java.util.List;

/**
 * Created by Dutru Thomas on 13/05/2019.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Result> mList;
    private RecyclerViewHolderListener listener;

    public RecyclerViewAdapter(List<Result> list, RecyclerViewHolderListener listener) {
        this.mList = list;
        this.listener = listener;
    }

    public List<Result> getList() {
        return mList;
    }

    public void setList(List<Result> list) {
        mList = list;
        notifyDataSetChanged(); // refresh data
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Result result = mList.get(position);
        holder.title.setText(result.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(holder, result, position);
            }
        });
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