package com.spheremall.core.api.configuration;

import com.spheremall.core.BuildConfig;

public interface ApiConstants {
    int CONNECT_TIMEOUT = 40;
    int READ_TIMEOUT = 40;
    int WRITE_TIMEOUT = 40;

    String API_GATEWAY_URL = BuildConfig.API_GATEWAY_URL;
    String API_VERSION = "v1";
    String API_USER_AGENT_PREFIX = "_AGENT_";
    String API_CLIENT_ID_TITLE = "client_id";
    String API_SECRET_TITLE = "client_secret";
    String API_CLIENT_ID = BuildConfig.API_CLIENT_ID;
    String API_SECRET_KEY = BuildConfig.API_SECRET_KEY;
}
