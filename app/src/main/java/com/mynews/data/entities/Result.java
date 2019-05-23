package com.mynews.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Dutru Thomas on 22/05/2019.
 */
public class Result {
    @SerializedName("title")
    String title;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    // Setter
    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
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
