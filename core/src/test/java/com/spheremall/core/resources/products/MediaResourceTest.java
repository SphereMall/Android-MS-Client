package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.Media;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MediaResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        Media media = client.media().first().data();

        Assert.assertNotNull(media);
        Assert.assertEquals(Media.class.getSimpleName(), media.getClass().getSimpleName());
    }
}
