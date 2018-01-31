package com.spheremall.core.entities.users;

import com.spheremall.core.entities.Entity;

import java.util.List;

public class Token extends Entity {
    public List<Data> data;
    public Boolean success;

    public class Data{
        public String token;
        public String expiries;
        public boolean isGuest;
        public String typeguest;
        public String model;
    }
}
