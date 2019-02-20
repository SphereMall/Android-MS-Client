package com.spheremall.core.entities;

import com.spheremall.core.jsonapi.annotations.Type;

@Type("range")
public class Range extends Entity implements Cloneable {
    public int max;
    public int min;
    public String type;
    public String identity;
}
