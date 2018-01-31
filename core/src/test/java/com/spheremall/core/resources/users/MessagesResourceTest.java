package com.spheremall.core.resources.users;

import com.spheremall.core.entities.users.Message;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MessagesResourceTest extends SetUpResourceTest {

    @Test
    public void testGetList() throws EntityNotFoundException, IOException, ServiceException {
        List<Message> messageList = client.messages().limit(1).all().data();
        Assert.assertNotNull(messageList);
        Assert.assertEquals(1, messageList.size());

        for (Message message : messageList) {
            Assert.assertEquals(Message.class.getSimpleName(), message.getClass().getSimpleName());
        }
    }
}
