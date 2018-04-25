package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.Currency;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class CurrenciesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetList() throws SphereMallException, IOException {
        List<Currency> currencyList = client.currencies().limit(2).all().data();
        Assert.assertNotNull(currencyList);
        Assert.assertEquals(2, currencyList.size());
    }
}