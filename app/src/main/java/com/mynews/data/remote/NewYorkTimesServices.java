package com.mynews.data.remote;

import com.mynews.data.entities.Root;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Dutru Thomas on 23/05/2019.
 */

public interface NewYorkTimesServices {
    @GET("topstories/v2/home.json?api-key=qn43KpoBbYTQdMXbOPSaJbv4h6vzAv5x")
    Call<Root> getTopStories();
}