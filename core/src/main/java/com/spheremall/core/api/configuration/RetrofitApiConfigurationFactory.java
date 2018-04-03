package com.spheremall.core.api.configuration;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.spheremall.core.api.configuration.ApiConstants.CONNECT_TIMEOUT;
import static com.spheremall.core.api.configuration.ApiConstants.READ_TIMEOUT;
import static com.spheremall.core.api.configuration.ApiConstants.WRITE_TIMEOUT;

public class RetrofitApiConfigurationFactory implements ApiConfigurationFactory<Retrofit> {

    public final String endpoint;
    public final boolean debug;

    public RetrofitApiConfigurationFactory(String endpoint, Boolean debug) {
        this.endpoint = endpoint;
        this.debug = debug;
    }

    @Override
    public Retrofit createConfiguration() {
        return createComponent(createApiClient());
    }

    private Retrofit createComponent(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    private OkHttpClient createApiClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (debug) {
            final HttpLoggingInterceptor httpLoggingInterceptor =
                    new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        return builder
                .addNetworkInterceptor(new RequestInterceptor())
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
