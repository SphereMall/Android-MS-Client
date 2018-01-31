package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.ProductAttributeValue;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ProductAttributeValuesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        ProductAttributeValue productAttributeValue = client.productAttributeValues()
                .first().data();

        Assert.assertNotNull(productAttributeValue);
        Assert.assertEquals(ProductAttributeValue.class.getSimpleName(), productAttributeValue.getClass().getSimpleName());
    }
}
