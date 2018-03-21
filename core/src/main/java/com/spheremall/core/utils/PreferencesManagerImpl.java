package com.spheremall.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManagerImpl implements PreferencesManager {

    private final String SPHEREMALL_CLIENT = "MS_ANDROID_CLIENT";
    private final String TOKEN = "TOKEN";
    private SharedPreferences preferences;

    public PreferencesManagerImpl(Context context) {
        preferences = context.getSharedPreferences(SPHEREMALL_CLIENT, Context.MODE_PRIVATE);
    }

    @Override
    public void setToken(String token) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    @Override
    public String getToken() {
        return preferences.getString(TOKEN, "");
    }
}
