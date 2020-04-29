package com.dbmi.seeds.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Resource not found exception.
 *
 * @author Givantha Kalansuriya
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

    /**  
     */
    public ResourceNotFoundException() {
        this("Unable to find the requested URL.");
    } // DEFAULT CONSTRUCTOR

    /**
     * @param message the message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    } // CONSTRUCTOR(STRING)
} // CLASS
