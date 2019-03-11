package com.spheremall.core.resources.users;

import com.spheremall.core.entities.users.Company;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CompaniesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetFirst() throws SphereMallException, IOException {
        Company company = client.companies().first().data();
        Assert.assertNotNull(company);
        Assert.assertEquals(Company.class.getSimpleName(), company.getClass().getSimpleName());
    }
}
