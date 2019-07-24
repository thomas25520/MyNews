package com.mynews.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Dutru Thomas on 18/07/2019.
 */
public class SharedPreferencesManager {
    private static SharedPreferences mPreferences;

    // Save any string in sharedPreferences
    public static void putString(Context context, String key, String value) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mPreferences.edit().putString(key, value).apply();
    }

    // Get string in sharedPreferences
    public static String getString(Context context, String key) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mPreferences.getString(key, "");
    }

    // Save boolean in sharedPreferences
    public static void putBoolean(Context context, String key, Boolean value) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mPreferences.edit().putBoolean(key, value).apply();
    }

    // Get boolean in sharedPreferences
    public static Boolean getBoolean(Context context, String key) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mPreferences.getBoolean(key, false);
    }
}