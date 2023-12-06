package com.shiptime.demo.exception;

public class BusinessException extends Exception {
    // Parameterless Constructor
    public BusinessException() {}

    // Constructor that accepts a message
    public BusinessException(String message)
    {
        super(message);
    }
}
