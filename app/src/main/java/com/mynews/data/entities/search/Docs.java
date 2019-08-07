package com.mynews.data.entities.search;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Dutru Thomas on 14/06/2019.
 */
public class Docs {
    @SerializedName("web_url")
    private String url;

    @SerializedName("abstract")
    private String title;

    @SerializedName("pub_date")
    private Date date;

    @SerializedName("section_name")
    private String category;

    @SerializedName("subsection_name")
    private String subCategory;

    @SerializedName("multimedia")
    private List<MultimediaSearch> multimedia;

    // Getter
    public String getUrl() {
        return url;
    }
    public String getTitle() {
        return title;
    }
    public Date getDate() {
        return date;
    }
    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public List<MultimediaSearch> getMultimediaSearch() {
        return multimedia;
    }

    // Setter
    public void setUrl(String url) {
        this.url = url;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public void setMultimedia(List<MultimediaSearch> multimedia) {
        this.multimedia = multimedia;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public Docs toObject(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, this.getClass());
    }

    // prevents the application from crashing if the article does not contain an image or link
    public boolean hasImage() {
        if (multimedia != null)
            return multimedia.size() > 0 && multimedia.get(0).getUrl() != null;
        return false;
    }
}