package com.spheremall.core.entities;

import com.spheremall.core.jsonapi.annotations.Type;

@Type("entities")
public class SMEntity extends Entity {

    public int visible;
    public int inRelationWithFactor;

    public String code;
    public String title;
    public String table;
    public String where;
    public String displayField;
}
