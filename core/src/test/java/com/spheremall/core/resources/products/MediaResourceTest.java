package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.Media;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MediaResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        Media media = client.media().first().data();

        Assert.assertNotNull(media);
        Assert.assertEquals(Media.class.getSimpleName(), media.getClass().getSimpleName());
    }
}
