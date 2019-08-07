package com.mynews.data.entities.search;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dutru Thomas on 14/06/2019.
 */
public class SearchResponse {
    @SerializedName("docs")
    private List<Docs> docs;

    @SerializedName("meta")
    private Meta meta;

    public SearchResponse() {
    }

    // Getter
    public List<Docs> getDocs() {
        return docs;
    }

    public Meta getMeta() {
        return meta;
    }

    // Setter
    public void setDocs(List<Docs> docs) {
        this.docs = docs;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public SearchResponse toObject(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, this.getClass());
    }
}
