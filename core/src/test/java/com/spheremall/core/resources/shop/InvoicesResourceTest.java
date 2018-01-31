package com.spheremall.core.resources.shop;

import com.spheremall.core.entities.shop.Invoice;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class InvoicesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        Invoice invoice = client.invoices().first().data();
        Assert.assertNotNull(invoice);
        Assert.assertEquals(Invoice.class.getSimpleName(), invoice.getClass().getSimpleName());
    }
}
