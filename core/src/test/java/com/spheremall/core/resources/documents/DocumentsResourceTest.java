package com.spheremall.core.resources.documents;


import com.spheremall.core.entities.documents.Document;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class DocumentsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        Document document = client.documents().first().data();
        Assert.assertNotNull(document);
    }

    @Test
    public void testDetailById() throws IOException, SphereMallException {
        int id = client.documents().first().data().getId();
        Document document = client.documents().detail(id);
        Assert.assertNotNull(document);
    }

    @Test
    public void testDetailByUrl() throws IOException, SphereMallException {
        String urlCode = client.documents().first().data().urlCode;
        Document document = client.documents().detail(urlCode);
        Assert.assertNotNull(document);
    }

    @Test
    public void testDetail() throws IOException, SphereMallException {
        List<Document> documents = client.documents().detail().data();
        Assert.assertNotNull(documents);
        Assert.assertTrue(documents.size() > 0);
    }
}
