package com.guarderia.products_ms.exceptions;

public class ProductIdAlreadyUsedException extends  RuntimeException{
    public ProductIdAlreadyUsedException(String message) {
        super(message);
    }
}
