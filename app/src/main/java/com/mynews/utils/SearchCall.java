package com.mynews.utils;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.mynews.data.entities.search.RootSearch;
import com.mynews.data.remote.RetrofitManager;
import com.mynews.myInterface.RootSearchCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dutru Thomas on 17/06/2019.
 */
public class SearchCall {
    public void search(final RootSearchCallBack callBack, EditText query, String beginDate, String endDate) {
        Call<RootSearch> call = RetrofitManager.getInstance().getSearch(query.getText().toString(), beginDate, endDate, "", 0, RetrofitManager.getApiKey());
        call.enqueue(new Callback<RootSearch>() {
            @Override
            public void onResponse(@NonNull Call<RootSearch> call, @NonNull Response<RootSearch> response) {
                if (response.body() != null)
                    callBack.onResponse(response.body().getSearchResponse());
            }

            @Override
            public void onFailure(@NonNull Call<RootSearch> call, @NonNull Throwable t) {

            }
        });
    }
}

