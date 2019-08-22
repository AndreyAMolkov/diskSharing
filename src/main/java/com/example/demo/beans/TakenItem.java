package com.example.demo.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "TakenItem")
@Table(name = "takenItems")
public class TakenItem {
    
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disk_id")
    private Disk disk;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "user_id")
    private User currentOwner;
    private boolean isFree;
    
    public TakenItem() {
        super();
    }
    public Disk getDisk() {
        return disk;
    }
    
    public void setDisk(@NotNull Disk disk) {
        this.disk = disk;
    }
    
    public User getCurrentOwner() {
        return currentOwner;
    }
    
    public void setCurrentOwner(User currentOwner) {
        this.currentOwner = currentOwner;
        setFree(currentOwner == null);
    }
    
    public TakenItem(Disk disk, User currentOwner) {
        super();
        setDisk(disk);
        setCurrentOwner(currentOwner);
        
    }
    
    public TakenItem(Disk disk) {
        super();
        setDisk(disk);
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public boolean isFree() {
        return isFree;
    }
    
    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }
}
