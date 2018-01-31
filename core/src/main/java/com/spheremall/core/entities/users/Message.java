package com.spheremall.core.entities.users;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("userMessages")
public class Message extends Entity {
    public int senderId;
    public int recipientId;
    public String subject;
    public String name;
    public String surname;
    public String email;
    public String address;
    public String postcode;
    public String message;
    public String date;
    public int accepted;
}
