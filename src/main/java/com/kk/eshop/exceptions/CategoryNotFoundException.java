package com.kk.eshop.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }

}
