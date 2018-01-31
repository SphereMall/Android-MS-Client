package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.CurrencyRate;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class CurrenciesRateResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        CurrencyRate currencyRate = client.currenciesRate().first().data();
        Assert.assertNotNull(currencyRate);
        Assert.assertEquals(CurrencyRate.class.getSimpleName(), currencyRate.getClass().getSimpleName());
    }
}
