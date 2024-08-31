package com.kk.eshop.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
