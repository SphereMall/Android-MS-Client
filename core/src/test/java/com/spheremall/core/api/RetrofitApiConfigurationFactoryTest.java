package com.spheremall.core.api;

import com.spheremall.core.api.configuration.ApiConfigurationFactory;
import com.spheremall.core.api.configuration.ApiConstants;
import com.spheremall.core.api.configuration.RetrofitApiConfigurationFactory;

import org.junit.Assert;
import org.junit.Test;

import retrofit2.Retrofit;

public class RetrofitApiConfigurationFactoryTest {

    @Test
    public void testCreateRetrofitApiConfigurationFactory() {
        ApiConfigurationFactory<Retrofit> apiConfigurationFactory = new RetrofitApiConfigurationFactory(ApiConstants.API_GATEWAY_URL);
        Retrofit retrofit = apiConfigurationFactory.createConfiguration();

        Assert.assertEquals(apiConfigurationFactory.getClass().getCanonicalName(), RetrofitApiConfigurationFactory.class.getCanonicalName());
        Assert.assertNotNull(retrofit);
    }
}
