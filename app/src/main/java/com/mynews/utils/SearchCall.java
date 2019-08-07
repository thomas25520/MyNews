package com.mynews.utils;

import android.support.annotation.NonNull;

import com.mynews.callbacks_interfaces.RootSearchCallBack;
import com.mynews.data.entities.search.RootSearch;
import com.mynews.data.remote.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dutru Thomas on 17/06/2019.
 */
public class SearchCall {
    public void search(final RootSearchCallBack rootSearchCallBack, String query, String fq, String beginDate, String endDate) {
        Call<RootSearch> call = RetrofitManager.getInstance().getSearch(query, beginDate, endDate, fq, RetrofitManager.getApiKey());
        call.enqueue(new Callback<RootSearch>() {
            @Override
            public void onResponse(@NonNull Call<RootSearch> call, @NonNull Response<RootSearch> response) {
                if (response.body() != null)
                    rootSearchCallBack.onResponse(response.body().getSearchResponse());
            }

            @Override
            public void onFailure(@NonNull Call<RootSearch> call, @NonNull Throwable t) {
                rootSearchCallBack.onFailure();
            }
        });
    }
//
//    public void test(final RootSearchCallBack rootSearchCallBack) {
//        rootSearchCallBack.onResponse(new SearchResponse());
//        rootSearchCallBack.onFailure();
//    }
}