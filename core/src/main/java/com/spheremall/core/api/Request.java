package com.spheremall.core.api;

import com.google.gson.Gson;
import com.spheremall.core.SMClient;
import com.spheremall.core.api.auth.AuthToken;
import com.spheremall.core.api.configuration.ApiConfigurationFactory;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.configuration.RetrofitApiConfigurationFactory;
import com.spheremall.core.api.providers.ApiServiceProvider;
import com.spheremall.core.api.providers.RetrofitServiceProvider;
import com.spheremall.core.api.response.ErrorResponse;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.api.services.SMService;
import com.spheremall.core.exceptions.BadGatewayException;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ForbiddenException;
import com.spheremall.core.exceptions.NotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.exceptions.UnauthorizedException;
import com.spheremall.core.resources.BaseResource;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Request implements BaseRequest {

    protected SMClient client;
    protected BaseResource resource;
    protected String headerAuth, headerUserAgent;
    protected AuthToken authToken;
    protected Method method;
    protected String uriAppend;
    protected HashMap<String, String> queryParams;
    protected SMService service;

    public Request(SMClient client, BaseResource resource) {
        this.client = client;
        this.resource = resource;
    }

    @Override
    public ResponseMonada handle(Method method, String uriAppend, HashMap<String, String> queryParams) throws SphereMallException, IOException {
        this.authToken = new AuthToken(client);
        this.method = method;
        this.uriAppend = uriAppend;
        this.queryParams = queryParams;
        this.service = getService();

        Response<ResponseBody> response = executeCall(false);

        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            throw new EntityNotFoundException();
        }

        try {
            String responseString = responseBody.string();
            return new ResponseMonada(responseString);
        } finally {
            responseBody.close();
        }
    }

    private Response<ResponseBody> executeCall(boolean isRepeated) throws SphereMallException, IOException {
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

    private Response<ResponseBody> handleError(Response<ResponseBody> response, boolean isRepeated) throws IOException, SphereMallException {
        try {
            if ((response.code() == 403 || response.code() == 401) && !isRepeated) {
                authToken.refreshToken();
                return executeCall(true);
            }

            String errorBody = response.errorBody().string();
            Gson gson = new Gson();
            ErrorResponse error = gson.fromJson(errorBody, ErrorResponse.class);

            if (client.isDebug()) {
                System.out.println(errorBody);
            }

            switch (response.code()) {
                case 502: {
                    throw new BadGatewayException(error);
                }
                case 403: {
                    throw new ForbiddenException(error);
                }
                case 401: {
                    throw new UnauthorizedException(error);
                }
                case 404: {
                    throw new NotFoundException(error);
                }
                default: {
                    throw new ServiceException(error);
                }
            }

        } catch (JSONException e) {
            throw new ServiceException(e.getLocalizedMessage());
        }
    }

    private SMService getService() {
        ApiConfigurationFactory<Retrofit> apiConfigurationFactory = new RetrofitApiConfigurationFactory(client);
        ApiServiceProvider apiServiceProvider = new RetrofitServiceProvider(apiConfigurationFactory);
        return apiServiceProvider.sphereMallService();
    }

    private Call<ResponseBody> getCallMethod() {
        String uri = resource.getURI();
        if (!uriAppend.isEmpty()) {
            uri += "/" + uriAppend;
        }
        switch (method) {
            case GET: {
                return service.get(
                        client.getHeaders(),
                        headerAuth,
                        headerUserAgent,
                        resource.getVersion(),
                        uri,
                        queryParams);
            }
            case POST:
                return service.create(
                        client.getHeaders(),
                        headerAuth,
                        headerUserAgent,
                        resource.getVersion(),
                        uri,
                        queryParams
                );
            case PUT:
                return service.update(
                        client.getHeaders(),
                        headerAuth,
                        headerUserAgent,
                        resource.getVersion(),
                        uri,
                        queryParams);
            case DELETE:
                return service.delete(
                        client.getHeaders(),
                        headerAuth,
                        headerUserAgent,
                        resource.getVersion(),
                        uri,
                        queryParams);
            default: {
                return null;
            }
        }

    }

    private void setAuthorization() {
        headerAuth = authToken.getAuthToken();
        headerUserAgent = authToken.getAuthUserAgent();
    }
}
