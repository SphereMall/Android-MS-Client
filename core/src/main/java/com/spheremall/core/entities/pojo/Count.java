package com.spheremall.core.entities.pojo;

import com.spheremall.core.entities.Entity;

import java.util.List;

public class Count extends Entity {

    public List<Data> data;
    public Boolean success;

    public class Data {
        public int id;
        public String type;
        public Attributes attributes;
    }

    public class Attributes {
        public int id;
        public int count;
    }
}