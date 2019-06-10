package com.mynews.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dutru Thomas on 23/05/2019.
 */
public class RetrofitManager {

    private static NewYorkTimesServices INSTANCE = null;
    private static final String BaseUrl = "https://api.nytimes.com/svc/";
    private static String ApiKey = "qn43KpoBbYTQdMXbOPSaJbv4h6vzAv5x";

    private RetrofitManager() {
    }

    public static String getApiKey() {
        return ApiKey;
    }

    public static NewYorkTimesServices getInstance() {
        if (INSTANCE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create()) // Convert Json to object
                    .baseUrl(BaseUrl)
                    .build();
            INSTANCE = retrofit.create(NewYorkTimesServices.class);
        }
        return INSTANCE;
    }
}
