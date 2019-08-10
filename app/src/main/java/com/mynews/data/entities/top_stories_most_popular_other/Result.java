package com.mynews.data.entities.top_stories_most_popular_other;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Dutru Thomas on 22/05/2019.
 */
public class Result {
    // Serialisation
    @SerializedName("section") // API string name
    private String category; // My string name

    @SerializedName("subsection")
    private String subsection;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("published_date")
    private Date publishedDate;

    @SerializedName("multimedia")
    private List<Multimedia> multimedia;

    @SerializedName("media")
    private List<Media> media;

    public Result() {
    }

    // prevents the application from crashing if the article does not contain an image or link
    public boolean hasImage() {
        if (multimedia != null)
            return multimedia.size() > 0 && multimedia.get(0).getUrl() != null;
        return false;
        // The same as
//        if (multimedia.size() > 0 && multimedia.get(0).getUrl() != null)
//            return true;
//        else
//            return false;
    }

    // Getter
    public String getCategory() {
        return category;
    }

    public List<Media> getMedia() {
        return media;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    // Setter
    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
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

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    @NonNull
    @Override
    public String toString() {
        return "Result{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", publishedDate=" + publishedDate +
                '}';
    }
}