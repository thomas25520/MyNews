package com.mynews.data.entities.search;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dutru Thomas on 09/07/2019.
 */
public class Meta {
    @SerializedName("hits")
    private int hits;

    public int getHits() {
        return hits / 10; // /10 return the number of article, info on API docs
    }

    public void setHits(int hits) {
        this.hits = hits;
    }
}