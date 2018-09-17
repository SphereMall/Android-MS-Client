package com.spheremall.core.resources.grapher;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Facets;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.grid.AttributeFilter;
import com.spheremall.core.filters.grid.EntityFilter;
import com.spheremall.core.filters.grid.FunctionalNameFilter;
import com.spheremall.core.filters.grid.GridFilter;
import com.spheremall.core.filters.grid.PriceRangeFilter;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class GridResourceTest extends SetUpResourceTest {

    @Test
    public void testAll() throws SphereMallException, IOException {
        List<Entity> gridItems = client.grid().all().data();
        Assert.assertNotNull(gridItems);
    }

    @Test
    public void testGridFilter() throws SphereMallException, IOException {
        GridFilter gridFilter = new GridFilter();
        gridFilter.elements(new EntityFilter("products"), new PriceRangeFilter(10, 4000));

        List<Entity> entities = client.grid()
                .filters(gridFilter)
                .all().data();

        Assert.assertNotNull(entities);
        Assert.assertTrue(entities.size() > 0);

        for (Entity entity : entities) {
            Assert.assertEquals(Product.class.getSimpleName().toLowerCase(), entity.getType());
        }
    }

    @Test
    public void testGridFacets() throws SphereMallException, IOException {
        GridFilter gridFilter = new GridFilter();
        gridFilter
                .elements(
                        new AttributeFilter(2661),
                        new AttributeFilter(2634),
                        new AttributeFilter(2627),
                        new EntityFilter("products"),
                        new FunctionalNameFilter(1));

        Assert.assertNotNull(gridFilter.toParams());
        Response<Facets> facets = client.grid()
                .filters(gridFilter)
                .facets();
        Facets facetsEntity = facets.data();
        Assert.assertNotNull(facetsEntity);
        Assert.assertNotNull(facetsEntity.attributes);
        Assert.assertNotNull(facetsEntity.priceRanges);
    }
}