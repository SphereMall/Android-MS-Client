package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.Product;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ProductResourceTest extends SetUpResourceTest {
    @Test
    public void testProductFull() throws SphereMallException, IOException {

        Product testProduct = client.products().first().data();

        List<Product> products = client.products()
                .full().data();

        Assert.assertNotNull(products);

        products = client.products()
                .limit(1)
                .ids(testProduct.getId())
                .full().data();

        Assert.assertEquals(1, products.size());
        Assert.assertEquals(testProduct.getId(), products.get(0).getId());

        Product product = client.products()
                .filters(new Predicate("showInSpecList", FilterOperators.EQUAL, "1"))
                .full(testProduct.getId())
                .data();

        Assert.assertEquals(testProduct.getId().intValue(), product.getId().intValue());

        product = client.products()
                .full(testProduct.urlCode).data();

        Assert.assertEquals(testProduct.urlCode, product.urlCode);
    }
}