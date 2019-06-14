package com.mynews.data.entities.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dutru Thomas on 14/06/2019.
 */
public class SearchResponse {
    @SerializedName("docs")
    private List<Docs> docs;

    public SearchResponse() {
    }

    // Getter
    public List<Docs> getDocs() {
        return docs;
    }

    // Setter
    public void setDocs(List<Docs> docs) {
        this.docs = docs;
    }
}
