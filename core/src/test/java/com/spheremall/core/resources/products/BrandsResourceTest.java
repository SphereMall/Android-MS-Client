package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.Brand;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class BrandsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetList() throws SphereMallException, IOException {
        List<Brand> brands = client.brands()
                .limit(3)
                .all().data();

        Assert.assertNotNull(brands);
        Assert.assertTrue(brands.size() > 0);

        for (Brand item : brands) {
            junit.framework.Assert.assertEquals(Brand.class.getSimpleName(), item.getClass().getSimpleName());
        }
    }
}
