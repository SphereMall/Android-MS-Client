package com.spheremall.core.entities.pojo;

import com.spheremall.core.entities.Entity;

public class UserId extends Entity {

    public Data data;

    public class Data {
        public String type;
        public int userId;
    }

    public class Attributes {
        public int id;
        public int count;
    }
}