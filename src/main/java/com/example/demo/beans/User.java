package com.example.demo.beans;

import java.util.List;

public class User {
    
    private long id;
    private String name;
    private List<Disk> listDisk;
    private List<Disk> listForChange;
    
    public User(long id, String name, List<Disk> listDisk) {
        super();
        this.id = id;
        this.name = name;
        this.listDisk = listDisk;
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
    
    public List<Disk> getListDisk() {
        return listDisk;
    }
    
    public void setListDisk(List<Disk> listDisk) {
        this.listDisk = listDisk;
    }
    
}
