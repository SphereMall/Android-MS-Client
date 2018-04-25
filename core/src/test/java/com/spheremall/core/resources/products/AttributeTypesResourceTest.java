package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.AttributeType;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class AttributeTypesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetList() throws SphereMallException, IOException {
        List<AttributeType> attributeGroups = client.attributeTypes()
                .limit(2)
                .all().data();

        Assert.assertNotNull(attributeGroups);
        Assert.assertEquals(2, attributeGroups.size());
        for (AttributeType item : attributeGroups) {
            Assert.assertEquals(AttributeType.class.getSimpleName(), item.getClass().getSimpleName());
        }
    }
}
