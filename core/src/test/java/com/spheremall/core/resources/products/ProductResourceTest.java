package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ProductResourceTest extends SetUpResourceTest {
    @Test
    public void testProductFull() throws EntityNotFoundException, IOException, ServiceException {
        List<Product> products = client.products()
                .limit(2)
                .full().data();
        Assert.assertNotNull(products);
        Assert.assertEquals(2, products.size());

        products = client.products()
                .limit(1)
                .ids(6351)
                .full().data();
        Assert.assertEquals(1, products.size());
        Assert.assertEquals(6351, products.get(0).getId().intValue());

        Product product = client.products()
                .full(6351).data();
        Assert.assertEquals(6351, product.getId().intValue());

        product = client.products()
                .full("verswitte-ciabatta-beenham").data();

        Assert.assertEquals("verswitte-ciabatta-beenham", product.urlCode);

        Assert.assertNotNull(products.get(0).productAttributeValues);
        Assert.assertNotNull(products.get(0).brands);
        Assert.assertNotNull(products.get(0).functionalNames);
    }
}