package com.mynews.callbacks_interfaces;

import com.mynews.data.entities.top_stories_most_popular_other.Result;

import java.util.List;

/**
 * Created by Dutru Thomas on 17/06/2019.
 */
public interface ResponseCallback {
    void onResponse(List<Result> result);

    void onFailure(Throwable throwable);
}