package com.mynews.data.remote;

import com.mynews.data.entities.search.RootSearch;
import com.mynews.data.entities.top_stories_most_popular_other.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Dutru Thomas on 23/05/2019.
 */

public interface NewYorkTimesServices {
    // EXAMPLE FOR @Query():
    // @GET("/friends")
    // Call<Root> friends(@Query("page") int page);  ==> ?page=451
    // Calling friends(451) become /friends?page=451.

    // EXAMPLE FOR @Path();
    // @GET("/friends/{id}")
    // Call<Root> example(@Path("id") int id);

    @GET("topstories/v2/{section}.json")
    Call<Root> getTopStoriesFrom(@Path("section") String section, @Query("api-key") String apiKey);

    @GET("mostpopular/v2/{articleTypes}/{period}.json")
    Call<Root> getMostPopularFrom(@Path("articleTypes") String articleTypes, @Path("period") int period, @Query("api-key") String apiKey);

    @GET("search/v2/articlesearch.json")
    Call<RootSearch> getSearch(@Query("q") String Query, @Query("begin_date") String begin_date, @Query("end_date") String end_date, @Query("fq") String FilterQuery, @Query("api-key") String apiKey);
}