package com.spheremall.core.resources.elasticSearch;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.products.ProductAttributeValue;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.ESSearchFilter;
import com.spheremall.core.filters.elasticsearch.compound.BoolFilter;
import com.spheremall.core.filters.elasticsearch.criterions.TermsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.terms.TermsFilter;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ElasticSearchResourceTest extends SetUpResourceTest {

    @Test
    public void testSetCompoundFilter() throws IOException, SphereMallException {

        TermsFilter termsFilter = new TermsFilter(new TermsFilterCriteria("variantsCompound", "fd4e12b4-df24-8882-d068-b73d26949e1f"));

        BoolFilter boolFilter = new BoolFilter();
        boolFilter.filter(termsFilter);

        ESSearchFilter filter = new ESSearchFilter();
        filter.index("sm-products", "sm-documents")
                .query(boolFilter);

        Response<List<Entity>> entities = client.elasticSearch()
                .filters(filter)
                .fetch();

        Assert.assertNotNull(entities);

        int count = Integer.valueOf(entities.meta().get("count").toString());
        int tmp = 0;
        for (Entity entity : entities.data()) {
            tmp++;
            Product product = (Product) entity;
            for (ProductAttributeValue attr : product.productAttributeValues) {
                if (attr.attributes.get(0).code.equals("colorbas")) {
                    System.out.println("found");
                }
            }
        }
        Assert.assertEquals(count, tmp);
    }

    @Test
    public void testSearch() throws IOException, SphereMallException {
        Response<List<Entity>> entities = client.elasticSearch()
                .limit(2)
                .offset(0)
                .search("milner");

        Assert.assertNotNull(entities);
        Assert.assertEquals(entities.data().size(), 2);
    }
}
