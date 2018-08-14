package com.spheremall.core.api.response;

import com.google.gson.Gson;

public class ResponseMonada {

    private final ErrorResponse errorResponse;
    private final String response;
    private final String status;

    public ResponseMonada(String response) {
        this.response = response;
        this.errorResponse = checkError();
        this.status = checkStatus();
    }

    private String checkStatus() {
        Gson gson = new Gson();
        StatusResponse statusResponse = gson.fromJson(response, StatusResponse.class);
        return statusResponse.status;
    }

    public boolean hasError() {
        return !this.status.equals("OK") && !this.status.equals("MULTI_STATUS");
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