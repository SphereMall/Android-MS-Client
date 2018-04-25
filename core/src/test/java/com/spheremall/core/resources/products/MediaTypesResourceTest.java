package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.MediaType;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MediaTypesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        MediaType mediaType = client.mediaTypes()
                .first().data();

        Assert.assertNotNull(mediaType);
        Assert.assertEquals(MediaType.class.getSimpleName(), mediaType.getClass().getSimpleName());
    }
}
