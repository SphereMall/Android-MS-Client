package com.spheremall.core.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    public List<Error> errors = new ArrayList<>();

    public class Error {
        public String message = "";
        @SerializedName("error_code")
        public String errorCode = "";
    }
}
