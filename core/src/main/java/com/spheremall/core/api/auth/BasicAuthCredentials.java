package com.spheremall.core.api.auth;

public class BasicAuthCredentials {

    public final String userName;
    public final String password;

    public BasicAuthCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
