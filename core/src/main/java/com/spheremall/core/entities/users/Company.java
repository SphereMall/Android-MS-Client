package com.spheremall.core.entities.users;

import com.spheremall.core.jsonapi.annotations.Type;
import com.spheremall.core.entities.Entity;

@Type("companies")
public class Company extends Entity {
    public String companyName;
    public String city;
    public String zipCode;
    public String country;
    public String street;
    public int houseNumber;
    public int kvkNumber;
}
