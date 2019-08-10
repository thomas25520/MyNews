package com.mynews.controller.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mynews.R;
import com.mynews.data.entities.search.Docs;
import com.mynews.data.entities.top_stories_most_popular_other.Result;
import com.mynews.utils.RecyclerViewHolderListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Dutru Thomas on 13/05/2019.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Result> mResultList = null;
    public static List<Docs> mDocsList = null;
    private RecyclerViewHolderListener mListener;
    private boolean isMultimediaRequired = true;

    // Constructor
    public RecyclerViewAdapter(List<Result> resultList, RecyclerViewHolderListener listener) {
        this.mResultList = resultList;
        this.mListener = listener;
    }

    public RecyclerViewAdapter() {
    }

    // SETTER
    public void setResultList(List<Result> resultList) {
        mResultList = resultList;
        notifyDataSetChanged(); // refresh data
    }

    public void setDocsList(List<Docs> docsList) {
        mDocsList = docsList;
        notifyDataSetChanged(); // refresh data
    }

    public void setListener(RecyclerViewHolderListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        if (mResultList == null && mDocsList == null)
            return 0;
        else if (mResultList != null)
            return mResultList.size();
        else
            return mDocsList.size();
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");

        if (mResultList != null) {
            final Result result = mResultList.get(position);
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
                    mListener.onItemClicked(holder, result, position);
                }
            });
        } else {
            if (mDocsList != null) {
                final Docs docs = mDocsList.get(position);
                if (docs != null && docs.getTitle() != null && docs.getDate() != null) {
                    if (docs.getSubCategory() == null) {
                        holder.categories.setText(docs.getCategory());
                    } else {
                        if (docs.getSubCategory().isEmpty()) {
                            holder.categories.setText(docs.getCategory());
                        } else {
                            holder.categories.setText(docs.getCategory() + " > " + docs.getSubCategory());
                        }
                    }

                    if (isMultimediaRequired) {
                        if (docs.hasImage())
                            Picasso.get().load(docs.getMultimediaSearch().get(0).getUrl()).into(holder.picture);
                    }
                    holder.title.setText(docs.getTitle());
                    holder.date.setText(sdf.format(docs.getDate()));
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.onItemClicked(holder, docs, position);
                        }
                    });
                }
            }
        }
    }

    public void setMultimediaRequired(boolean isMultimediaRequired) {
        this.isMultimediaRequired = isMultimediaRequired;
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