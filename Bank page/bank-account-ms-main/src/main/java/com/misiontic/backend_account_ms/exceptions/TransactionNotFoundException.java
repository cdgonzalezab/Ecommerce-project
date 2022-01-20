package com.misiontic.backend_account_ms.exceptions;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message){
        super(message);
    }
}