package com.example.demo.beans;

public class Disk {
    private long id;
    private String name;
    private User master;
    private User currentOwner;
    
    public Disk() {
        ;// empty
    }
    
    public Disk(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    
}
