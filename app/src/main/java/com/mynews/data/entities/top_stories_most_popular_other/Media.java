package com.mynews.data.entities.top_stories_most_popular_other;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dutru Thomas on 12/06/2019.
 */
public class Media {
    @SerializedName("media-metadata")
    private List<MediaMetadata> mediaMetadataList;

    //getter
    public List<MediaMetadata> getMediaMetadataList() {
        return mediaMetadataList;
    }

    //setter
    public void setMediaMetadataList(List<MediaMetadata> mediaMetadataList) {
        this.mediaMetadataList = mediaMetadataList;
    }
}