package com.spheremall.core.api.configuration;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request modifiedRequest;
        Request initialRequest = chain.request();
        modifiedRequest = initialRequest.newBuilder()
                .addHeader("DeviceName", "Android")
                .build();

        return chain.proceed(modifiedRequest);
    }
}