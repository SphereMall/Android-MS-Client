package com.spheremall.core.api.response;

public class ErrorResponse {
    public boolean success;
    public Error error = new Error();

    public class Error {
        public String message = "";
    }
}
