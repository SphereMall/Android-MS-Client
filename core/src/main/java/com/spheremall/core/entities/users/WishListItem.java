package com.spheremall.core.entities.users;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("wishListItems")
public class WishListItem extends Entity {
    public int userId;
    public int entityId;
    public int objectId;
    public String createDate;
    public String updateDate;

    public Entity entity;
}
