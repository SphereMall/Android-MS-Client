package com.spheremall.core.exceptions;

import android.annotation.TargetApi;
import android.os.Build;

import com.spheremall.core.api.response.ErrorResponse;

public class SphereMallException extends Throwable {

    private ErrorResponse error = null;

    public SphereMallException() {
        super();
    }

    public SphereMallException(ErrorResponse error) {
        this.error = error;
    }

    public SphereMallException(String message) {
        super(message);
    }

    public SphereMallException(String message, Throwable cause) {
        super(message, cause);
    }

    public SphereMallException(Throwable cause) {
        super(cause);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected SphereMallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ErrorResponse getError() {
        return error;
    }

    public boolean hasErrorResponse() {
        return error != null;
    }
}
