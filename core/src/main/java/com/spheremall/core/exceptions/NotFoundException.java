package com.spheremall.core.exceptions;

import com.spheremall.core.api.response.ErrorResponse;

public class NotFoundException extends SphereMallException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(ErrorResponse error) {
        super(error);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    protected NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
