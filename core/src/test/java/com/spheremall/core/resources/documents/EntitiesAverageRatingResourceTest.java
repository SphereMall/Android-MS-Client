package com.spheremall.core.resources.documents;

import com.spheremall.core.entities.documents.EntityAverageRating;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class EntitiesAverageRatingResourceTest extends SetUpResourceTest {

    @Test
    public void testGet() throws IOException, SphereMallException {
        List<EntityAverageRating> entityAverageRatings = client.entitiesAverageRating().all().data();
        Assert.assertNotNull(entityAverageRatings);
    }
}
