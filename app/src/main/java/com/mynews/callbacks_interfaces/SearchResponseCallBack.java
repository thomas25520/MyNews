package com.mynews.callbacks_interfaces;

import com.mynews.data.entities.search.SearchResponse;

/**
 * Created by Dutru Thomas on 17/06/2019.
 */
public interface SearchResponseCallBack {
    void onResponse(SearchResponse searchResponse);

    void onFailure(Throwable throwable);
}
