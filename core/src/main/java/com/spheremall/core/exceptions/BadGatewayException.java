package com.spheremall.core.exceptions;

import com.spheremall.core.api.response.ErrorResponse;

public class BadGatewayException extends SphereMallException {

    public BadGatewayException() {
        super();
    }

    public BadGatewayException(ErrorResponse error) {
        super(error);
    }

    public BadGatewayException(String message) {
        super(message);
    }

    public BadGatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadGatewayException(Throwable cause) {
        super(cause);
    }

    protected BadGatewayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
