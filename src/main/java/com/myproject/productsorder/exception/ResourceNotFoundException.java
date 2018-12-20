package com.myproject.productsorder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO: EXCEPTION FOR CONTROLLERS ORDER FOR CONTROLLERS!!!

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
        super();
    }

    // THIS IS USED IN CONTROLLERS
    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

}
