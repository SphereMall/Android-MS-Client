package com.spheremall.core.utils;

public class StaticPreferencesManager implements PreferencesManager {

    public static String staticToken = "";

    @Override
    public void setToken(String token) {
        staticToken = token;
    }

    @Override
    public String getToken() {
        return staticToken;
    }
}
