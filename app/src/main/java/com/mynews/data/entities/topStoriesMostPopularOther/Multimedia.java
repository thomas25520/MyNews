package com.mynews.data.entities.topStoriesMostPopularOther;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dutru Thomas on 06/06/2019.
 */
public class Multimedia {
    @SerializedName("url")
    private String url;

    // Getter
    public String getUrl() {
        return url;
    }

    // Setter
    public void setUrl(String url) {
        this.url = url;
    }
}