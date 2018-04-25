package com.spheremall.core.api.response;

import java.util.ArrayList;
import java.util.List;

public class ReceivedTypes {
    public List<Data> data = new ArrayList<>();
    public List<Data> included = new ArrayList<>();

    public static class Data {
        public String type;
    }
}
