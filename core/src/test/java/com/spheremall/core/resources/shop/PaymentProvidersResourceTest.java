package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.PaymentProvider;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class PaymentProvidersResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        PaymentProvider paymentProvider = client.paymentProviders().first().data();
        Assert.assertNotNull(paymentProvider);
    }
}
