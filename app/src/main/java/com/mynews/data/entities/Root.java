package com.mynews.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dutru Thomas on 22/05/2019.
 */
public class Root {
    @SerializedName("results")
    List<Result> results;

    public Root() {
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
