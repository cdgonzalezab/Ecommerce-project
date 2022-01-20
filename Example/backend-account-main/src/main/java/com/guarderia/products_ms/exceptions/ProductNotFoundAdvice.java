package com.guarderia.products_ms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody

public class ProductNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler (ProductNotFoundException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    String EntityNotFoundAdvice (ProductNotFoundException ex){
        return ex.getMessage();
    }
}
