package com.vpr.app.exceptions;

import lombok.Getter;

@Getter
public class VprEntityNotFoundException extends RuntimeException {

    public VprEntityNotFoundException(String message) {
        super(message);
    }

}