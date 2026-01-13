package com.onsyatra_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // 400 Forbidden
public class BackendUserNotAvailableException extends RuntimeException {

    public BackendUserNotAvailableException(String message) {
        super(message);
    }

    public BackendUserNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
