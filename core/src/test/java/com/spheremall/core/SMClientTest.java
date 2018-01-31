package com.spheremall.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.spheremall.core.resources.products.ProductResource;
import com.spheremall.core.resources.products.ProductResourceImpl;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SMClientTest {

    private final String gatewayUrl = "";
    private final String clientId = "client_id";
    private final String secretKey = "secret_key";
    private final String version = "v1";
    private SharedPreferences preferences;
    private Context context;

    @Before
    public void setUp() {
        preferences = Mockito.mock(SharedPreferences.class);
        context = Mockito.mock(Context.class);
        Mockito.when(context.getSharedPreferences("SPHEREMALL_PREFERENCES", 0)).thenReturn(preferences);
    }

    @Test
    public void testCreateSMClient() {
        SMClient smClient = SMClient.initialize(context, gatewayUrl, clientId, secretKey, version);
        Assert.assertEquals(smClient.getClass().getCanonicalName(), SMClient.class.getCanonicalName());
    }

    @Test
    public void testSMClientGetters() {
        SMClient smClient = SMClient.initialize(context, gatewayUrl, clientId, secretKey, version);

        Assert.assertEquals(smClient.getClientId(), clientId);
        Assert.assertEquals(smClient.getGatewayUrl(), gatewayUrl);
        Assert.assertEquals(smClient.getSecretKey(), secretKey);
        Assert.assertEquals(smClient.getVersion(), version);
    }

    @Test
    public void testGetProductResource() {
        SMClient smClient = SMClient.initialize(context, gatewayUrl, clientId, secretKey, version);
        ProductResource productResource = smClient.products();
        Assert.assertEquals(ProductResourceImpl.class.getCanonicalName(), productResource.getClass().getCanonicalName());
    }
}
