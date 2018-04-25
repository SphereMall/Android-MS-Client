package com.spheremall.core.exceptions;

import com.spheremall.core.api.response.ErrorResponse;

public class EntityNotFoundException extends SphereMallException {

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(ErrorResponse error) {
        super(error);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
