package com.mynews.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mynews.controller.model.Search;

/**
 * Created by Dutru Thomas on 20/03/2019.
 */

public class SharedPreferencesManager {
    private static SharedPreferences mPreferences;

    // Save any string in sharedPreferences
    public static void putString(Context context, String key, String value) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mPreferences.edit().putString(key, value).apply();
    }

    // Get string
    public static String getString(Context context, String key) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mPreferences.getString(key, "");
    }

    // Save search object in sharedPreferences
    public static void putSearch(Context context, String key, Search search) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = search.formatToJson();
        mPreferences.edit().putString(key, json).apply();
    }

    // Get Search object
    public static Search getSearch(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Search search = new Search();
        return search.jsonToSearch(mPreferences.getString(Constants.SEARCH_OBJECT, ""));
    }
}