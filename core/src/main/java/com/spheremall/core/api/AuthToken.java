package com.spheremall.core.api;


import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.ApiConfigurationFactory;
import com.spheremall.core.api.configuration.ApiConstants;
import com.spheremall.core.api.configuration.RetrofitApiConfigurationFactory;
import com.spheremall.core.api.provides.ApiServiceProvider;
import com.spheremall.core.api.provides.RetrofitServiceProvider;
import com.spheremall.core.api.services.AuthService;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.utils.PreferencesManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AuthToken {

    private SMClient client;
    private PreferencesManager preferencesManager;
    protected AuthService service;

    public AuthToken(SMClient client) {
        this.client = client;
        this.preferencesManager = client.getPreferencesManager();
        this.service = getAuthService();
    }

    public String getAuthToken() {
        String token = preferencesManager.getToken();
        if (!token.isEmpty()) {
            token = "Bearer " + token;
        }
        return token;
    }

    public String getAuthUserAgent() {
        return ApiConstants.API_USER_AGENT_PREFIX + SMClient.userAgent;
    }

    public void refreshToken() throws IOException, NullPointerException, ServiceException, JSONException {
        HashMap<String, String> params = new HashMap<>();
        params.put(ApiConstants.API_CLIENT_ID_TITLE, client.getClientId());
        params.put(ApiConstants.API_SECRET_TITLE, client.getSecretKey());
        mapTokenResponse(service.getToken(getAuthUserAgent(), client.getVersion(), "admin/token", params).execute());
    }

    private void mapTokenResponse(Response<ResponseBody> response) throws ServiceException, JSONException, IOException {
        if (!response.isSuccessful()) {
            throw new ServiceException();
        }
        JSONObject mainObject = new JSONObject(response.body().string());
        JSONArray data = mainObject.getJSONArray("data");
        JSONObject tokenObj = data.getJSONObject(0);
        String token = tokenObj.getString("token");
        if (token.isEmpty()) {
            throw new ServiceException();
        }
        preferencesManager.setToken(token);
    }

    protected AuthService getAuthService() {
        ApiConfigurationFactory<Retrofit> apiConfigurationFactory = new RetrofitApiConfigurationFactory(client);
        ApiServiceProvider apiServiceProvider = new RetrofitServiceProvider(apiConfigurationFactory);
        return apiServiceProvider.authService();
    }
}
