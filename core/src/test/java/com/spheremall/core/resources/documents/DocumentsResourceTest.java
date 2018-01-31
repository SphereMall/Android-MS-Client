package com.spheremall.core.resources.documents;


import com.spheremall.core.entities.documents.Document;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DocumentsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        Document document = client.documents().first().data();

        Assert.assertNotNull(document);
        Assert.assertEquals(Integer.valueOf(9318), document.getId());
    }

}
