package com.mynews.data.entities.search;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dutru Thomas on 09/07/2019.
 */
public class Meta {
    @SerializedName("hits")
    private int hits;

    public int getNumberOfArticles() {
        return hits;
    }

    public void setNumberOfArticles(int hits) {
        this.hits = hits;
    }
}
