package com.spheremall.core.resources.grapher;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.filters.grid.EntityFilter;
import com.spheremall.core.filters.grid.GridFilter;
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
    public void testGridFilter() throws EntityNotFoundException, IOException, ServiceException {
        GridFilter gridFilter = new GridFilter();
        gridFilter.elements(new EntityFilter("products"));

        List<Entity> entities = client.grid()
                .filters(gridFilter)
                .all().data();
        for (Entity entity : entities) {
            Assert.assertEquals(Product.class.getSimpleName().toLowerCase(), entity.getType());
        }
    }

    @Test
    public void testGridFacets() {

    }
}