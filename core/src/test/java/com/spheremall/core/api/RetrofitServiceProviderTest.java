package com.spheremall.core.api;

import com.spheremall.core.api.configuration.ApiConfigurationFactory;
import com.spheremall.core.api.configuration.ApiConstants;
import com.spheremall.core.api.configuration.RetrofitApiConfigurationFactory;
import com.spheremall.core.api.provides.ApiServiceProvider;
import com.spheremall.core.api.provides.RetrofitServiceProvider;
import com.spheremall.core.api.services.SMService;

import org.junit.Assert;
import org.junit.Test;

import retrofit2.Retrofit;

public class RetrofitServiceProviderTest {

    @Test
    public void testCreateSMService() {
        ApiConfigurationFactory<Retrofit> apiConfigurationFactory = new RetrofitApiConfigurationFactory(ApiConstants.API_GATEWAY_URL, true);
        ApiServiceProvider apiServiceProvider = new RetrofitServiceProvider(apiConfigurationFactory);
        SMService sphereMallService = apiServiceProvider.sphereMallService();

        Assert.assertEquals(apiServiceProvider.getClass().getCanonicalName(), RetrofitServiceProvider.class.getCanonicalName());
        Assert.assertNotNull(sphereMallService);
    }
}
