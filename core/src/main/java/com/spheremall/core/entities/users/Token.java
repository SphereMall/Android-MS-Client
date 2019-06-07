package com.spheremall.core.entities.users;

import com.google.gson.annotations.SerializedName;
import com.spheremall.core.entities.Entity;

import java.util.List;

public class Token extends Entity {
    public List<Data> data;
    public Boolean success;

    public class Data {
        @SerializedName("accessToken")
        public String token;
        @SerializedName("refreshToken")
        public String refreshToken;
        @SerializedName("expires_in")
        public String expiries;
        public boolean isGuest;
        public String typeguest;
        public Model model;
    }

    public class Model {
        public String id;
    }
}
