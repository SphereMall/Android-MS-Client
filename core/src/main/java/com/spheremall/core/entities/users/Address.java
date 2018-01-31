package com.spheremall.core.entities.users;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.HashMap;
import java.util.Map;

@Type("addresses")
public class Address extends Entity {
    public int userId;
    public String name;
    public String surname;
    public String email;
    public String countryName;
    public String cityName;
    public String street;
    public String zipCode;
    public String phoneNumber;
    public String companyName;
    public String clientNumber;
    public String state;

    public HashMap<String, String> asParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", String.valueOf(userId));
        params.put("name", name);
        params.put("surname", surname);
        params.put("email", email);
        params.put("countryName", countryName);
        params.put("cityName", cityName);
        params.put("street", street);
        params.put("zipCode", zipCode);
        params.put("phoneNumber", phoneNumber);
        params.put("companyName", companyName);
        params.put("clientNumber", clientNumber);
        params.put("state", state);

        for (Map.Entry<String, String> item : getProperties().entrySet()) {
            params.put(item.getKey(), item.getValue());
        }
        return params;
    }
}
