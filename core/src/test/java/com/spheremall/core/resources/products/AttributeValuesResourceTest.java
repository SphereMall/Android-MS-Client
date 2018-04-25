package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class AttributeValuesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        AttributeValue attributeValue = client.attributeValues().first().data();
        Assert.assertNotNull(attributeValue);
        Assert.assertEquals(AttributeValue.class.getSimpleName(), attributeValue.getClass().getSimpleName());
    }
}
