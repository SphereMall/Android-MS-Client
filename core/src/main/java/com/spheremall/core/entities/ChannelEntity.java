package com.spheremall.core.entities;

import com.spheremall.core.jsonapi.annotations.Type;

@Type("channelEntities")
public class ChannelEntity extends Entity {
    public int entityId;
    public int objectId;
    public int channelId;
}
