package com.spheremall.core.di;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.auth.BasicAuthInterceptor;
import com.spheremall.core.api.configuration.RequestInterceptor;
import com.spheremall.core.api.services.AuthService;
import com.spheremall.core.api.services.SMService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.spheremall.core.api.configuration.ApiConstants.CONNECT_TIMEOUT;
import static com.spheremall.core.api.configuration.ApiConstants.READ_TIMEOUT;
import static com.spheremall.core.api.configuration.ApiConstants.WRITE_TIMEOUT;

@Module
public class ApiModule {

    private final SMClient smClient;

    public ApiModule(SMClient smClient) {
        this.smClient = smClient;
    }

    @Provides
    @Singleton
    public Retrofit createComponent(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(smClient.getGatewayUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    public OkHttpClient httpClient(Interceptor requestInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (smClient.isDebug()) {
            final HttpLoggingInterceptor httpLoggingInterceptor =
                    new HttpLoggingInterceptor()
                            .setLevel(smClient.getLoggingLevel());
            builder.addInterceptor(httpLoggingInterceptor);
        }

        if (smClient.getBasicAuth() != null) {
            builder.addInterceptor(new BasicAuthInterceptor(smClient.getBasicAuth()));
        }

        return builder
                .addNetworkInterceptor(requestInterceptor)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    public Interceptor requestInterceptor() {
        return new RequestInterceptor();
    }

    @Provides
    public SMService sphereMallService(Retrofit retrofit) {
        return retrofit.create(SMService.class);
    }

    @Provides
    public AuthService authService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }
}
