package com.spheremall.core.resources.products;

import com.spheremall.core.entities.products.Option;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class OptionsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        Option option = client.options()
                .first().data();

        Assert.assertNotNull(option);
        Assert.assertEquals(Option.class.getSimpleName(), option.getClass().getSimpleName());
    }
}
