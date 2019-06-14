package com.mynews.utils;

import android.support.annotation.NonNull;

import com.mynews.data.entities.topStoriesMostPopularOther.Result;
import com.mynews.data.entities.topStoriesMostPopularOther.Root;
import com.mynews.data.remote.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dutru Thomas on 30/05/2019.
 */
public class CategoriesCall implements Callback<Root> {

    // instance callback from Callback<Root>
    @Override
    public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
        // do nothing
    }

    @Override
    public void onFailure(@NonNull Call<Root> call, @NonNull Throwable t) {
    }

    // Top stories reference
    public void topStoriesHome(final Callbacks callbacksTabCategoriesFragment) {
        Call<Root> call = RetrofitManager.getInstance().getTopStoriesFrom(Section.home.toString(), RetrofitManager.getApiKey());
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
                if (response.body() != null)
                    callbacksTabCategoriesFragment.onResponse(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<Root> call, @NonNull Throwable t) {
                callbacksTabCategoriesFragment.onFailure();
            }
        });
    }

    // Automobile reference
    public void topStoryFrom(final Callbacks callbacksTabCategoriesFragment) {
        Call<Root> call = RetrofitManager.getInstance().getTopStoriesFrom(Section.automobiles.toString(), RetrofitManager.getApiKey());
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
                if (response.body() != null)
                    callbacksTabCategoriesFragment.onResponse(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<Root> call, @NonNull Throwable t) {
                callbacksTabCategoriesFragment.onFailure();
            }
        });
    }

    // MostPopular reference
    public void mostPopular(final Callbacks callbacksTabCategoriesFragment) {
        Call<Root> call = RetrofitManager.getInstance().getMostPopularFrom(ArticleTypes.views.toString(), PeriodType.seven.toInt(), RetrofitManager.getApiKey());
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
                if (response.body() != null)
                    callbacksTabCategoriesFragment.onResponse(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<Root> call, @NonNull Throwable t) {
                callbacksTabCategoriesFragment.onFailure();
            }
        });
    }

    // Creating interface for callback
    public interface Callbacks {
        void onResponse(List<Result> result);

        void onFailure();
    }
}