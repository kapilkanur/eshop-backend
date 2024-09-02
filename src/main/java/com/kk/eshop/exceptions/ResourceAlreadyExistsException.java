package com.kk.eshop.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {

    /**
     * Resource already exists exception.
     * @param message exception message
     */
    public ResourceAlreadyExistsException(final String message) {
        super(message);
    }

}

