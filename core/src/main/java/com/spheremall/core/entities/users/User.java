package com.spheremall.core.entities.users;

import com.spheremall.core.jsonapi.annotations.Type;
import com.spheremall.core.entities.Entity;

@Type("users")
public class User extends Entity {

    public int viewId;
    public int active;
    public int isSubscriber;
    public int defaultAddressId;
    public int langId;
    public int basketId;
    public String name;
    public String password;
    public String email;
    public String surname;
    public String guid;
    public String avatar;
}