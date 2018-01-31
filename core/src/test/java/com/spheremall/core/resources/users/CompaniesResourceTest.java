package com.spheremall.core.resources.users;

import com.spheremall.core.entities.users.Company;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class CompaniesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws EntityNotFoundException, IOException, ServiceException {
        Company company = client.companies().first().data();
        Assert.assertNotNull(company);
        Assert.assertEquals(Company.class.getSimpleName(), company.getClass().getSimpleName());
    }
}
