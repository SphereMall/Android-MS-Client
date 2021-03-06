package com.spheremall.core.api.providers;

import com.spheremall.core.api.configuration.ApiConfigurationFactory;
import com.spheremall.core.api.services.AuthService;
import com.spheremall.core.api.services.SMService;

import retrofit2.Retrofit;

public class RetrofitServiceProvider implements ApiServiceProvider {

    protected final Retrofit retrofit;

    public RetrofitServiceProvider(ApiConfigurationFactory<Retrofit> apiConfigurationFactory) {
        this.retrofit = apiConfigurationFactory.createConfiguration();
    }

    @Override
    public SMService sphereMallService() {
        return this.retrofit.create(SMService.class);
    }

    @Override
    public AuthService authService() {
        return this.retrofit.create(AuthService.class);
    }
}
