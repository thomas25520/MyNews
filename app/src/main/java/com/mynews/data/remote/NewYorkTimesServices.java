package com.mynews.data.remote;

import android.support.annotation.Nullable;

import com.mynews.data.entities.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Dutru Thomas on 23/05/2019.
 */

public interface NewYorkTimesServices {
    @GET("topstories/v2/{section}.json")
    Call<Root> getTopStoriesFrom(@Path("section") String section, @Query("api-key") String apiKey);

    @GET("mostpopular/v2/{articleTypes}/{period}.json")
    Call<Root> getMostPopularFrom(@Nullable @Path("articleTypes") String articleTypes, @Path("period") int period, @Query("api-key") String apiKey);
}