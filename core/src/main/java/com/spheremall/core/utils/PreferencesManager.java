package com.spheremall.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    private final String SPHEREMALL_CLIENT = "MS_ANDROID_CLIENT";
    private final String TOKEN = "TOKEN";
    private SharedPreferences preferences;

    public PreferencesManager(Context context) {
        preferences = context.getSharedPreferences(SPHEREMALL_CLIENT, Context.MODE_PRIVATE);
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public String getToken(){
        return preferences.getString(TOKEN, "");
    }
}
