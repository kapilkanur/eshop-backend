package com.kk.eshop.exceptions;

public class ProductNotFoundException extends RuntimeException {

    /**
     * Product not found exception.
     * @param exceptionMessage exception message
     */
    public ProductNotFoundException(final String exceptionMessage) {
        super(exceptionMessage);
    }
}
