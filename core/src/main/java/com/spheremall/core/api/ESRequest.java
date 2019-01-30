package com.spheremall.core.api;

import com.google.gson.Gson;
import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ElasticSearchError;
import com.spheremall.core.api.response.ErrorResponse;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.exceptions.BadGatewayException;
import com.spheremall.core.exceptions.ForbiddenException;
import com.spheremall.core.exceptions.NotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.exceptions.UnauthorizedException;
import com.spheremall.core.resources.BaseResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class ESRequest extends com.spheremall.core.api.Request {

    protected static final String BODY = "body";
    protected static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public ESRequest(SMClient client, BaseResource resource) {
        super(client, resource);
    }

    @Override
    public ResponseMonada handle(Method method, String uriAppend, HashMap<String, String> queryParams) throws IOException, SphereMallException {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(client.getLoggingLevel());

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        StringBuilder urlBuilder = getURI(uriAppend);

        RequestBody requestBody = RequestBody.create(JSON, queryParams.get(BODY).getBytes(StandardCharsets.UTF_8));

        Request.Builder requestBuilder = new Request.Builder()
                .url(client.getGatewayUrl() + urlBuilder.toString())
                .post(requestBody);

        for (Map.Entry<String, String> entry : client.getHeaders().entrySet()) {
            requestBuilder.header(entry.getKey(), entry.getValue());
        }

        Request request = requestBuilder
                .build();

        Response response = okHttpClient.newCall(request).execute();

        if (response.body() == null) {
            return new ResponseMonada(createError(null));
        }

        if (response.isSuccessful()) {
            String responseString = response.body().string();
            return new ResponseMonada(responseString);
        } else {
            String errorResponse = response.body().string();
            ErrorResponse error = createError(errorResponse);
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
        }
    }

    private ErrorResponse createError(String errorString) {
        if (errorString == null) {
            ErrorResponse.Error error = new ErrorResponse.Error();
            error.errorCode = "1001";
            error.message = "Response is null";

            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.errors = new ArrayList<>();
            errorResponse.errors.add(error);
            return errorResponse;
        } else {
            try {
                Gson gson = new Gson();
                ElasticSearchError searchError = gson.fromJson(errorString, ElasticSearchError.class);
                ErrorResponse.Error error = new ErrorResponse.Error();
                error.errorCode = String.valueOf(searchError.status);
                error.message = searchError.error.causedBy.reason;

                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.errors = new ArrayList<>();
                errorResponse.errors.add(error);
                return errorResponse;
            } catch (Throwable e) {
                ErrorResponse.Error error = new ErrorResponse.Error();
                error.errorCode = "1002";
                error.message = e.getMessage();
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.errors = new ArrayList<>();
                errorResponse.errors.add(error);
                return errorResponse;
            }
        }
    }

    private StringBuilder getURI(String uriAppend) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("/");
        urlBuilder.append(client.getVersion());
        urlBuilder.append("/");
        urlBuilder.append(resource.getURI());
        if (uriAppend != null && !uriAppend.isEmpty()) {
            urlBuilder.append("/");
            urlBuilder.append(uriAppend);
        }
        return urlBuilder;
    }
}
