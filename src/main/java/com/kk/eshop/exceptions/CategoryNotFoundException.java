package com.kk.eshop.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    /**
     * Category not found exception.
     * @param exceptionMessage exception message
     */
    public CategoryNotFoundException(final String exceptionMessage) {
        super(exceptionMessage);
    }

}
