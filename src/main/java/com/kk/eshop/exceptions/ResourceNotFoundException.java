package com.kk.eshop.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    /**
     * Resource not found exception.
     * @param message exception message
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }

}
