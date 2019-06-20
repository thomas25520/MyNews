package com.mynews.controller.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mynews.R;
import com.mynews.data.entities.top_stories_most_popular_other.Result;
import com.mynews.utils.RecyclerViewHolderListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Dutru Thomas on 13/05/2019.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Result> mList;
    private RecyclerViewHolderListener listener;
    private boolean isMultimediaRequired = true;

    public RecyclerViewAdapter(List<Result> list, RecyclerViewHolderListener listener) {
        this.mList = list;
        this.listener = listener;
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

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");

        final Result result = mList.get(position);
        if (result.getSubsection() == null) {
            holder.categories.setText(result.getCategory());
        } else {
            if (result.getSubsection().isEmpty()) {
                holder.categories.setText(result.getCategory());
            } else {
                holder.categories.setText(result.getCategory() + " > " + result.getSubsection());
            }
        }

        if (isMultimediaRequired) {
            if (result.hasImage())     // prevents the application from crashing if the article does not contain an image or link
                Picasso.get().load(result.getMultimedia().get(0).getUrl()).into(holder.picture); // Use picasso library to display picture
        } else {
            Picasso.get().load(result.getMedia().get(0).getMediaMetadataList().get(0).getUrl()).into(holder.picture);
        }

        holder.title.setText(result.getTitle());
        holder.date.setText(sdf.format(result.getPublishedDate())); // Convert date with SimpleDateFormat
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(holder, result, position);
            }
        });
    }

    public void setMultimediaRequired(boolean isMultimediaRequired) {
        this.isMultimediaRequired = isMultimediaRequired;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView picture;
        private TextView categories;
        private TextView date;
        private TextView title;
        private TextView subsection;

        private MyViewHolder(final View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.fragment_tab_categories_item_img);
            categories = itemView.findViewById(R.id.fragment_tab_categories_item_categories);
            date = itemView.findViewById(R.id.fragment_tab_categories_item_date);
            title = itemView.findViewById(R.id.fragment_tab_categories_item_title);
//            subsection = itemView.findViewById(R.id.fragment_tab_categories_item_subsection);
        }
    }
}