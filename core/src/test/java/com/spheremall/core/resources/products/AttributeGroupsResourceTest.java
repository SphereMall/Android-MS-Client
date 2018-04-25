package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.AttributeGroup;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class AttributeGroupsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetList() throws SphereMallException, IOException {
        List<AttributeGroup> attributeGroups = client.attributeGroups()
                .in("id", "1", "2")
                .limit(2)
                .all().data();

        Assert.assertNotNull(attributeGroups);
        Assert.assertEquals(2, attributeGroups.size());
        for (AttributeGroup item : attributeGroups) {
            Assert.assertEquals(AttributeGroup.class.getSimpleName(), item.getClass().getSimpleName());
        }
    }
}
