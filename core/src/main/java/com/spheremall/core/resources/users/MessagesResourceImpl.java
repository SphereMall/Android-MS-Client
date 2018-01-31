package com.spheremall.core.resources.users;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.users.Message;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class MessagesResourceImpl extends BaseResource<Message, MessagesResource> implements MessagesResource {

    public MessagesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public MessagesResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "messages";
    }

    @Override
    public ObjectMaker<Message> initializeMaker() {
        return new ObjectMaker<>(Message.class);
    }

    @Override
    protected MessagesResource currentContext() {
        return this;
    }
}
