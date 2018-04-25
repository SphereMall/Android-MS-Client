package com.spheremall.core.exceptions;

import com.spheremall.core.api.response.ErrorResponse;

public class ServiceException extends SphereMallException {

    public ServiceException() {
    }

    public ServiceException(ErrorResponse error) {
        super(error);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
