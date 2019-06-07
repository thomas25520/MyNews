package com.mynews.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dutru Thomas on 06/06/2019.
 */
public class Multimedia {
    @SerializedName("format")
    private String format;

    @SerializedName("url")
    private String url;

    // Getter
    public String getFormat() {
        return format;
    }

    // Setter
    public void setFormat(String format) {
        this.format = format;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}