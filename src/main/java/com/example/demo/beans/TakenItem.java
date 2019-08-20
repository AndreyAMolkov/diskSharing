package com.example.demo.beans;

//@Entity
public class TakenItem {
    private Disk disk;
    private User user;
    
    public Disk getDisk() {
        return disk;
    }
    
    public void setDisk(Disk disk) {
        this.disk = disk;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public TakenItem(Disk disk, User user) {
        super();
        this.disk = disk;
        this.user = user;
    }
    
}
