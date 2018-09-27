package com.spheremall.core.resources.documents;

import com.spheremall.core.entities.documents.Comment;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class CommentsResourceTest extends SetUpResourceTest {

    @Test
    public void testGet() throws IOException, SphereMallException {
        List<Comment> comments = client.comments().all().data();
        Assert.assertNotNull(comments);
    }
}
