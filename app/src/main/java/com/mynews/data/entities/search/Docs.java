package com.mynews.data.entities.search;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dutru Thomas on 14/06/2019.
 */
public class Docs {
    @SerializedName("web_url")
    private String url;

    @SerializedName("abstract")
    private String title;

    @SerializedName("pub_date")
    private String publishedDate;

    // Getter
    public String getUrl() {
        return url;
    }

    // Setter
    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}
