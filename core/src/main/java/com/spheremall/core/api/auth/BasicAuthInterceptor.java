package com.spheremall.core.api.auth;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {

    public final BasicAuthCredentials authCredentials;

    public BasicAuthInterceptor(BasicAuthCredentials authCredentials) {
        this.authCredentials = authCredentials;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String credentials = Credentials.basic(authCredentials.userName, authCredentials.password);
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build();
        return chain.proceed(authenticatedRequest);
    }
}
