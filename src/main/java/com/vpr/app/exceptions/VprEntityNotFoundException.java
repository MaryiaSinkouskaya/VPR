package com.vpr.app.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class VprEntityNotFoundException extends RuntimeException {

    public VprEntityNotFoundException(String message) {
        super(message);
    }

}