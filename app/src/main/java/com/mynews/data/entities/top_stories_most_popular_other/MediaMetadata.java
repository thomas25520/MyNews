package com.mynews.data.entities.top_stories_most_popular_other;

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
