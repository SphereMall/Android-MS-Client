package com.spheremall.core.resources.products;

import com.spheremall.core.entities.SMEntity;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class EntitiesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetList() throws SphereMallException, IOException {
        List<SMEntity> entities = client.entities()
                .limit(4)
                .all().data();

        Assert.assertNotNull(entities);
        Assert.assertEquals(4, entities.size());
        for (SMEntity item : entities) {
            Assert.assertEquals(SMEntity.class.getSimpleName(), item.getClass().getSimpleName());
        }
    }
}