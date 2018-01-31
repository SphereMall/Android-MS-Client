package com.spheremall.core.api.response;

import java.util.List;

public class ReceivedTypes {
    public boolean success;
    public List<Data> data;
    public List<Data> included;

    public static class Data {
        public String type;
    }
}
