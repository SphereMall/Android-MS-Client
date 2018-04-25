package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.PaymentProvider;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class PaymentProvidersResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        PaymentProvider paymentProvider = client.paymentProviders().first().data();
        Assert.assertNotNull(paymentProvider);
    }
}
