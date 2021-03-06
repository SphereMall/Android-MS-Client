package com.spheremall.core;

import android.content.Context;

import com.spheremall.core.api.auth.BasicAuthCredentials;
import com.spheremall.core.utils.PreferencesManager;
import com.spheremall.core.utils.PreferencesManagerImpl;

import java.util.HashMap;
import java.util.Map;

import okhttp3.logging.HttpLoggingInterceptor;

public class SMClient implements ServiceInjector {
    protected String gatewayUrl;
    protected String clientId;
    protected String secretKey;
    protected String version = "v1";
    protected PreferencesManager preferencesManager;
    protected Boolean debug = false;
    private static SMClient client = null;
    protected HttpLoggingInterceptor.Level loggingLevel = HttpLoggingInterceptor.Level.BODY;
    protected BasicAuthCredentials credentials = null;
    protected Map<String, String> headers = new HashMap<>();

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

    public void setPreferencesManager(PreferencesManager preferencesManager) {
        this.preferencesManager = preferencesManager;
    }

    private SMClient(String gatewayUrl, String clientId, String secretKey, String version, Context context) {
        this.gatewayUrl = gatewayUrl;
        this.clientId = clientId;
        this.secretKey = secretKey;
        this.version = version;
        this.preferencesManager = new PreferencesManagerImpl(context);
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

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public Boolean isDebug() {
        return debug;
    }

    public void setLoggingLevel(HttpLoggingInterceptor.Level loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    public void setBacisAuth(BasicAuthCredentials basicAuth) {
        credentials = basicAuth;
    }

    public BasicAuthCredentials getBasicAuth() {
        return credentials;
    }

    public HttpLoggingInterceptor.Level getLoggingLevel() {
        return loggingLevel;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeader(String name, String value) {
        this.headers.put(name, value);
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}