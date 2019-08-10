package com.mynews.utils;

import android.support.annotation.NonNull;

import com.mynews.callbacks_interfaces.ResponseCallback;
import com.mynews.data.entities.top_stories_most_popular_other.Root;
import com.mynews.data.enums.ArticleTypes;
import com.mynews.data.enums.PeriodType;
import com.mynews.data.enums.Section;
import com.mynews.data.remote.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dutru Thomas on 30/05/2019.
 */
public class CategoriesCall {

    // Top stories reference
    public void getTopStoriesHome(final ResponseCallback topStoriesHomeResponseCallback) {
        Call<Root> call = RetrofitManager.getInstance().getTopStoriesFrom(Section.home.toString(), RetrofitManager.getApiKey());
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
                if (response.body() != null)
                    topStoriesHomeResponseCallback.onResponse(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<Root> call, @NonNull Throwable throwable) {
                topStoriesHomeResponseCallback.onFailure(throwable);
            }
        });
    }

    // Automobile reference
    public void getTopStoryAutomobile(final ResponseCallback topStoriesAutomobileResponseCallback) {
        Call<Root> call = RetrofitManager.getInstance().getTopStoriesFrom(Section.automobiles.toString(), RetrofitManager.getApiKey());
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
                if (response.body() != null)
                    topStoriesAutomobileResponseCallback.onResponse(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<Root> call, @NonNull Throwable throwable) {
                topStoriesAutomobileResponseCallback.onFailure(throwable);
            }
        });
    }

    // MostPopular reference
    public void getMostPopular(final ResponseCallback mostPopularResponseCallback) {
        Call<Root> call = RetrofitManager.getInstance().getMostPopularFrom(ArticleTypes.views.toString(), PeriodType.seven.toInt(), RetrofitManager.getApiKey());
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
                if (response.body() != null)
                    mostPopularResponseCallback.onResponse(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<Root> call, @NonNull Throwable throwable) {
                mostPopularResponseCallback.onFailure(throwable);
            }
        });
    }
}