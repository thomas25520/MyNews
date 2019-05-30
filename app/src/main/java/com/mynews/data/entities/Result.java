package com.mynews.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Dutru Thomas on 22/05/2019.
 */
public class Result {
    @SerializedName("title") // API string name
            String title; // My string name

    @SerializedName("abstract")
    String description;

    @SerializedName("url")
    String url;

    @SerializedName("published_date")
    Date publishedDate;

    public Result() {
    }

    // Getter
    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    // Setter
    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Result{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", publishedDate=" + publishedDate +
                '}';
    }
}
