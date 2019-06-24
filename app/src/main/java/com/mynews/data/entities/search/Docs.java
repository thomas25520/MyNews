package com.mynews.data.entities.search;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.mynews.data.entities.top_stories_most_popular_other.Multimedia;

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
    private List<Multimedia> multimedia;


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

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Setter
    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
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
