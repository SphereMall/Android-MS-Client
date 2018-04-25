package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.AttributeDisplayType;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class AttributeDisplayTypesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetList() throws SphereMallException, IOException {
        List<AttributeDisplayType> attributes = client.attributeDisplayTypes()
                .limit(2)
                .all().data();

        Assert.assertNotNull(attributes);
        Assert.assertEquals(2, attributes.size());
    }
}
