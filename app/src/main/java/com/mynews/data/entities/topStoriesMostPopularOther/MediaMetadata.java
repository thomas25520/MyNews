package com.mynews.data.entities.topStoriesMostPopularOther;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dutru Thomas on 12/06/2019.
 */
public class MediaMetadata {

    @SerializedName("url")
    private String url;

    //getter
    public String getUrl() {
        return url;
    }

    //setter
    public void setUrl(String url) {
        this.url = url;
    }

}
