package com.guarderia.products_ms.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public  ProductNotFoundException(String message){
        super(message);
    }
}
