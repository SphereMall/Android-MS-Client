package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class AttributeValuesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        AttributeValue attributeValue = client.attributeValues().first().data();
        Assert.assertNotNull(attributeValue);
        Assert.assertEquals(AttributeValue.class.getSimpleName(), attributeValue.getClass().getSimpleName());
    }
}
