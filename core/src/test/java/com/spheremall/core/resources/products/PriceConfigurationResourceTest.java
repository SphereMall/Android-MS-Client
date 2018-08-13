package com.spheremall.core.resources.products;

import com.spheremall.core.entities.price.PriceConfiguration;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class PriceConfigurationResourceTest extends SetUpResourceTest {

    @Test
    public void testGetPriceConfigurations() throws IOException, SphereMallException {
        List<PriceConfiguration> priceConfigurations = client.priceConfigurations()
                .all().data();

        Assert.assertNotNull(priceConfigurations);
    }
}
