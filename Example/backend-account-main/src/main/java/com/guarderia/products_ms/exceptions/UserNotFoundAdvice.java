package com.guarderia.products_ms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody

public class UserNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler (UserNotFoundException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    String UserNotFoundAdvice (UserNotFoundException ex){
        return ex.getMessage();
    }
}
