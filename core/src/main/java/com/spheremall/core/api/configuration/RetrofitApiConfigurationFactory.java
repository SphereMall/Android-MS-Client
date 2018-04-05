package com.spheremall.core.api.configuration;


import com.spheremall.core.SMClient;

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
    public final HttpLoggingInterceptor.Level level;

    public RetrofitApiConfigurationFactory(SMClient client) {
        this.endpoint = client.getGatewayUrl();
        this.debug = client.isDebug();
        this.level = client.getLoggingLevel();
    }

    public RetrofitApiConfigurationFactory(String endpoint, boolean debug) {
        this.endpoint = endpoint;
        this.debug = debug;
        this.level = HttpLoggingInterceptor.Level.BASIC;
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
                            .setLevel(level);
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
