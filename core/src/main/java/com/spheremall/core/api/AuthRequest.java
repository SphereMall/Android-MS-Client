package com.spheremall.core.api;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.ApiConfigurationFactory;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.configuration.RetrofitApiConfigurationFactory;
import com.spheremall.core.api.provides.ApiServiceProvider;
import com.spheremall.core.api.provides.RetrofitServiceProvider;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.api.services.AuthService;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.BaseResource;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AuthRequest implements BaseRequest {

    private SMClient client;
    private BaseResource resource;
    private String headerUserAgent;
    private AuthToken authToken;
    private Method method;
    private String uriAppend;
    private HashMap<String, String> queryParams;
    private AuthService service;

    public AuthRequest(SMClient client, BaseResource resource) {
        this.client = client;
        this.resource = resource;
    }

    @Override
    public ResponseMonada handle(Method method, String uriAppend, HashMap<String, String> queryParams) throws ServiceException, IOException, EntityNotFoundException {
        this.authToken = new AuthToken(client);
        this.method = method;
        this.uriAppend = uriAppend;
        this.queryParams = queryParams;
        service = getService();

        Response<ResponseBody> response = executeCall(false);

        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            throw new EntityNotFoundException();
        }

        return new ResponseMonada(responseBody.string());
    }

    private Response<ResponseBody> executeCall(boolean isRepeated) throws ServiceException, IOException {
        setAuthorization();
        Call<ResponseBody> call = getCallMethod();
        if (call == null) {
            throw new ServiceException();
        }
        Response<ResponseBody> response = call.execute();
        if (!response.isSuccessful() && response.errorBody() != null) {
            response = handleError(response, isRepeated);
        }
        return response;
    }

    private Response<ResponseBody> handleError(Response<ResponseBody> response, boolean isRepeated) throws IOException, ServiceException {
        try {
            String errorBody = response.errorBody().string();
            if (errorBody == null) {
                throw new ServiceException();
            }
            JSONObject mainObject = new JSONObject(errorBody);
            JSONObject error = mainObject.getJSONObject("error");
            String errorMessage = error.getString("message");
            if (errorMessage.contains("Access denied") && !isRepeated) {
                authToken.refreshToken();
                return executeCall(true);
            } else {
                throw new ServiceException();
            }
        } catch (JSONException e) {
            throw new ServiceException();
        }
    }

    private AuthService getService() {
        ApiConfigurationFactory<Retrofit> apiConfigurationFactory = new RetrofitApiConfigurationFactory(client.getGatewayUrl());
        ApiServiceProvider apiServiceProvider = new RetrofitServiceProvider(apiConfigurationFactory);
        return apiServiceProvider.authService();
    }

    private Call<ResponseBody> getCallMethod() {
        String uri = resource.getURI();
        if (!uriAppend.isEmpty()) {
            uri += "/" + uriAppend;
        }
        return service.getToken(headerUserAgent, resource.getVersion(), uri, queryParams);

    }

    private void setAuthorization() {
        headerUserAgent = authToken.getAuthUserAgent();
    }
}
