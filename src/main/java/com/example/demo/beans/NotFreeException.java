package com.example.demo.beans;

public class NotFreeException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public NotFreeException() {
        super();
    }
    
    public NotFreeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    public NotFreeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public NotFreeException(String message) {
        super(message);
    }
    
    public NotFreeException(Throwable cause) {
        super(cause);
    }
    
}
