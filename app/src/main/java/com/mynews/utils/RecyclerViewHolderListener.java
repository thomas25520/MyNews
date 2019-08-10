package com.mynews.utils;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Dutru Thomas on 29/05/2019.
 */
public interface RecyclerViewHolderListener<T, VH extends RecyclerView.ViewHolder> {
    void onItemClicked(VH vh, T item, int pos);
}