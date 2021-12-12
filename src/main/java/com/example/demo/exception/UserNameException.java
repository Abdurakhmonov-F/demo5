package com.example.demo.exception;

public class UserNameException extends RuntimeException{
    public UserNameException(){}

    public UserNameException (final String message){
        super(message);
    }
    public UserNameException (final String message, final Throwable cause) {
        super(message, cause);
    }
    public UserNameException (final Throwable cause){
        super(cause);
    }
    public UserNameException(final String message,
                             final Throwable cause,
                             final boolean enableSuppression,
                             final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    }
