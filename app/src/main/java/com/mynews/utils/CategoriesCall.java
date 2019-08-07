package com.mynews.utils;

import android.support.annotation.NonNull;

import com.mynews.callbacks_interfaces.RootCallback;
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
    public void getTopStoriesHome(final RootCallback callbacksTabCategoriesFragment) {
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
    public void getTopStoryAutomobile(final RootCallback callbacksTabCategoriesFragment) {
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
    public void getMostPopular(final RootCallback callbacksTabCategoriesFragment) {
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
}