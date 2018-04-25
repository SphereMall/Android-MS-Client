package com.spheremall.core.api.response;

import com.google.gson.Gson;

public class ResponseMonada {
    private final ErrorResponse errorResponse;
    private final String response;

    public ResponseMonada(String response) {
        this.response = response;
        this.errorResponse = checkError();
    }

    public boolean hasError() {
        return errorResponse != null;
    }

    public String getResponse() {
        return response;
    }

    public ErrorResponse getErrorResponse() {
        if (errorResponse == null) {
            return new ErrorResponse();
        }
        return errorResponse;
    }

    private ErrorResponse checkError() {
        Gson gson = new Gson();
        ErrorResponse errorResponse = gson.fromJson(response, ErrorResponse.class);
        if (errorResponse.errors.size() == 0) {
            errorResponse = null;
        }
        return errorResponse;
    }
}