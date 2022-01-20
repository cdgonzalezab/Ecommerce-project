package com.guarderia.products_ms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody

public class ProductIdAlreadyUsedAdvice {
    @ResponseBody
    @ExceptionHandler (ProductIdAlreadyUsedException.class)
    @ResponseStatus (HttpStatus.CONFLICT)
    String EntityNotFoundAdvice (ProductIdAlreadyUsedException ex){
        return ex.getMessage();
    }
}
