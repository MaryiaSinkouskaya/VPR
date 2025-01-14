package com.vpr.app.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class VprEntityNotFoundException extends RuntimeException {
    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public VprEntityNotFoundException(String message) {
        super(message);
    }

}