package com.mynews.myInterface;

import com.mynews.data.entities.topStoriesMostPopularOther.Result;

import java.util.List;

/**
 * Created by Dutru Thomas on 17/06/2019.
 */
public interface RootCallback {
    void onResponse(List<Result> result);

    void onFailure();
}