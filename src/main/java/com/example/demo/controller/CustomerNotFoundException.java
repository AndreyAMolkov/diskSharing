package com.example.demo.controller;

public class CustomerNotFoundException extends RuntimeException {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final Long id;
    
    public CustomerNotFoundException(Long id) {
        super("User ID[ " + id + "] - not found");
        this.id = id;
    }
    
    public Long getCustomerId() {
        return id;
    }
}
