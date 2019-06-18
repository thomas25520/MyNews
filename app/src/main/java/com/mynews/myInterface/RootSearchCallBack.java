package com.mynews.myInterface;

import com.mynews.data.entities.search.SearchResponse;

/**
 * Created by Dutru Thomas on 17/06/2019.
 */
public interface RootSearchCallBack {
    void onResponse(SearchResponse searchResponse);

    void onFailure();
}
