package com.guarderia.products_ms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody

public class AlreadyAddedAdvice {
    @ResponseBody
    @ExceptionHandler (AlreadyAddedException.class)
    @ResponseStatus (HttpStatus.CONFLICT)
    String AlreadyAddedAdvice (AlreadyAddedException ex){
        return ex.getMessage();
    }
}
