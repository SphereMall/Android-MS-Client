package com.spheremall.core.resources.products;

import com.spheremall.core.entities.documents.Document;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class AttributesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        Attribute attribute = client.attributes().first().data();
        Assert.assertNotNull(attribute);
    }

    @Test
    public void testAttributeBelong() throws SphereMallException, IOException {
        List<Attribute> attributesByEntity = client.attributes().belong(Document.class).data();
        Assert.assertNotNull(attributesByEntity);

        List<Attribute> attributesByEntityAndGroupId = client.attributes().belong(Document.class, 1).data();
        Assert.assertNotNull(attributesByEntityAndGroupId);
        Assert.assertEquals("Title", attributesByEntityAndGroupId.get(0).title);

        Attribute attributeByAllParams = client.attributes().belong(Document.class, 1, 1076).data();
        Assert.assertNotNull(attributeByAllParams);
        Assert.assertEquals(Integer.valueOf(1076), attributeByAllParams.getId());
    }
}
