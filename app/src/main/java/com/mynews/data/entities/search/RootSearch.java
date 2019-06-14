package com.mynews.data.entities.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dutru Thomas on 14/06/2019.
 */
public class RootSearch {
    @SerializedName("response")
    private List<SearchResponse> SearchResponse;

    public RootSearch() {
    }

    // Getter
    public List<com.mynews.data.entities.search.SearchResponse> getSearchResponse() {
        return SearchResponse;
    }

    // Setter
    public void setSearchResponse(List<com.mynews.data.entities.search.SearchResponse> searchResponse) {
        SearchResponse = searchResponse;
    }
}