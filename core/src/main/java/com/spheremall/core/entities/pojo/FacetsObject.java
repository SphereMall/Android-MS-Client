package com.spheremall.core.entities.pojo;

import com.spheremall.core.entities.Entity;

import java.util.List;

public class FacetsObject extends Entity {

    public Data data;

    public class Data {
        public List<Attribute> attributes;
        public PriceRangePojo priceRange;
    }

    public class Attribute {
        public Integer id;
        public int showInSpecList;
        public String code;
        public String title;
        public String description;
        public int attributeGroupId;
        public String cssClass;
        public String value;
        public String image;
        public int orderNumber;

        public String valueTitle;
        public int attributeId;
        public String displayType;
        public int useInFilter;
        public int amount;
    }

    public class PriceRangePojo {
        public int minPrice;
        public int maxPrice;
        public int amounts;
    }
}
