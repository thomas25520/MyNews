package com.mynews.data.entities.search;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dutru Thomas on 14/06/2019.
 */
public class RootSearch {
    @SerializedName("response")
    private SearchResponse searchResponse;

    public RootSearch() {
    }

    // Getter
    public SearchResponse getSearchResponse() {
        return searchResponse;
    }

    // Setter
    public void setSearchResponse(SearchResponse searchResponse) {
        this.searchResponse = searchResponse;
    }
}