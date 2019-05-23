package com.mynews.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dutru Thomas on 23/05/2019.
 */
public class RetrofitManager {

    private static NewYorkTimesServices INSTANCE = null;

    private RetrofitManager() {
    }

    public static NewYorkTimesServices getInstance() {
        if (INSTANCE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create()) // Convert Json to object
                    .baseUrl("https://api.nytimes.com/svc/")
                    .build();
            INSTANCE = retrofit.create(NewYorkTimesServices.class);
        }
        return INSTANCE;
    }
}
