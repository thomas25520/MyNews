package com.mynews.utils;

import com.mynews.data.entities.Result;
import com.mynews.data.entities.Root;
import com.mynews.data.remote.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dutru Thomas on 30/05/2019.
 */
public class CategoriesCall implements Callback<Root> {

    Callbacks mCallbacksTabCategoriesFragment;

    // instanciate callback from Callback<Root>
    @Override
    public void onResponse(Call<Root> call, Response<Root> response) {
        // do nothing
    }

    @Override
    public void onFailure(Call<Root> call, Throwable t) {
    }

    // Top stories reference
    public void topStories(final Callbacks callbacksTabCategoriesFragment) {
        mCallbacksTabCategoriesFragment = callbacksTabCategoriesFragment;
        Call<Root> call = RetrofitManager.getInstance().getTopStories();
        // call.enqueue(this); // car tu implement l'interface et tu fais override meme comportement pour tout le monde car dans le scope de la class
        call.enqueue(new Callback<Root>() { // comportement unique car dans scope de la method
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                callbacksTabCategoriesFragment.onResponse(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                callbacksTabCategoriesFragment.onFailure();
            }
        });
    }

    // Top stories reference
    public void test(final Callbacks callbacks) {
        Call<Root> call = RetrofitManager.getInstance().getTopStories();
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                List<Result> res = response.body().getResults();
                //recyclerAdapter.setList(res);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }

    // Creating interface
    public interface Callbacks {
        void onResponse(List<Result> result);

        void onFailure();
    }
}