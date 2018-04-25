package com.spheremall.core.exceptions;

import android.annotation.TargetApi;
import android.os.Build;

public class MethodNotFoundException extends RuntimeException {
    public MethodNotFoundException() {
    }

    public MethodNotFoundException(String message) {
        super(message);
    }

    public MethodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MethodNotFoundException(Throwable cause) {
        super(cause);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public MethodNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
