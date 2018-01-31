package com.spheremall.core.resources.users;

import com.spheremall.core.entities.users.WishListItem;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class WishListItemsResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        WishListItem item = client.wishListItems().first().data();

        Assert.assertNotNull(item);
        Assert.assertEquals(WishListItem.class.getSimpleName(), item.getClass().getSimpleName());
    }
}
