package com.spheremall.core;

import android.content.Context;

import com.spheremall.core.utils.PreferencesManager;

public class SMClient implements ServiceInjector {
    protected String gatewayUrl;
    protected String clientId;
    protected String secretKey;
    protected String version = "v1";
    protected PreferencesManager preferencesManager;

    private static SMClient client = null;

    public static final String userAgent = "SM_SDK_ANDROID_CLIENT";

    public static SMClient get() {
        if (client == null) {
            throw new IllegalStateException("You need initialize client with params:");
        }
        return client;
    }

    public static SMClient initialize(Context context, String gatewayUrl, String clientId, String secretKey, String version) {
        client = new SMClient(gatewayUrl, clientId, secretKey, version, context);
        return client;
    }

    private SMClient(String gatewayUrl, String clientId, String secretKey, String version, Context context) {
        this.gatewayUrl = gatewayUrl;
        this.clientId = clientId;
        this.secretKey = secretKey;
        this.version = version;
        this.preferencesManager = new PreferencesManager(context);
    }

    public String getClientId() {
        return clientId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getVersion() {
        return version;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public PreferencesManager getPreferencesManager() {
        return preferencesManager;
    }
}