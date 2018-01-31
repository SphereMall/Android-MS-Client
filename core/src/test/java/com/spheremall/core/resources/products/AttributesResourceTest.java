package com.spheremall.core.resources.products;

import com.spheremall.core.entities.documents.Document;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class AttributesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        Attribute attribute = client.attributes().get(3).data();
        Assert.assertNotNull(attribute);
        Assert.assertEquals(Integer.valueOf(3), attribute.getId());
    }

    @Test
    public void testAttributeBelong() throws EntityNotFoundException, IOException, ServiceException {
        List<Attribute> attributesByEntity = client.attributes().belong(Document.class).data();
        Assert.assertNotNull(attributesByEntity);
        Assert.assertEquals(Integer.valueOf(1076), attributesByEntity.get(0).getId());

        List<Attribute> attributesByEntityAndGroupId = client.attributes().belong(Document.class, 1).data();
        Assert.assertNotNull(attributesByEntityAndGroupId);
        Assert.assertEquals("Title", attributesByEntityAndGroupId.get(0).title);

        Attribute attributeByAllParams = client.attributes().belong(Document.class, 1, 1076).data();
        Assert.assertNotNull(attributeByAllParams);
        Assert.assertEquals(Integer.valueOf(1076), attributeByAllParams.getId());
    }
}
