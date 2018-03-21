package com.spheremall.core.entities;

public class UserId extends Entity {

    public Data data;
    public Boolean success;

    public class Data {
        public String type;
        public int userId;
    }

    public class Attributes {
        public int id;
        public int count;
    }
}