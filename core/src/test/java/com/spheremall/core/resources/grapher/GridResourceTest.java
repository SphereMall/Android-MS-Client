package com.spheremall.core.resources.grapher;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class GridResourceTest extends SetUpResourceTest {

    @Test
    public void testAll() throws EntityNotFoundException, IOException, ServiceException {
        List<Entity> gridItems = client.grid().all().data();
        Assert.assertNotNull(gridItems);
    }

    @Test
    public void testCount() throws EntityNotFoundException, ServiceException, IOException {
        int count = client.grid().count();
        Assert.assertEquals(94, count);
    }
}