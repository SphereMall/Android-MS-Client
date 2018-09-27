package com.spheremall.core.entities.documents;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("comments")
public class Comment extends Entity {

    public int parentId;
    public int userId;
    public String userName;
    public String userEmail;
    public int channelId;
    public String title;
    public String message;
    public double rating;
    public int entityId;
    public int objectId;
    public int visible;
    public String createDate;
    public String lastUpdate;
}
