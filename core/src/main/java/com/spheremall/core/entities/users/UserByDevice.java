package com.spheremall.core.entities.users;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("userDevices")
public class UserByDevice extends Entity {

    public int userId;
    public String deviceId;
    public String userType;
}
