package com.guarderia.products_ms.exceptions;

public class AlreadyAddedException extends RuntimeException{
    public AlreadyAddedException(String message) {
        super(message);
    }
}
