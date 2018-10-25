package com.spheremall.core.resources.users;

import com.spheremall.core.entities.users.ExternalUserRelation;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ExternalUserRelationResourceTest extends SetUpResourceTest {

    @Test
    public void testGetAll() throws IOException, SphereMallException {
        List<ExternalUserRelation> relations = client.externalUserRelationResource().all().data();
        Assert.assertNotNull(relations);
    }
}
