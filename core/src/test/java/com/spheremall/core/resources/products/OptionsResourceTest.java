package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.Option;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class OptionsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        Option option = client.options()
                .first().data();

        Assert.assertNotNull(option);
        Assert.assertEquals(Option.class.getSimpleName(), option.getClass().getSimpleName());
    }
}
