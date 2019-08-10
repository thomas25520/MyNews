package com.mynews.data.entities.search;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dutru Thomas on 22/06/2019.
 */
public class MultimediaSearch {
    @SerializedName("url")
    private String url;

    private String missedExtentionUrl = "https://static01.nyt.com/";

    // Getter
    public String getUrl() {
        return missedExtentionUrl + url;
    }

    // Setter
    public void setUrl(String url) {
        this.url = missedExtentionUrl + url;
    }
}