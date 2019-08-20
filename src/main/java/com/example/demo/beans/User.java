package com.example.demo.beans;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "Client")
@Table(name = "clients")
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credential_id")
    private Credential credential;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Disk> listDisk;
//    private List<Disk> listForChange;
    
    public User(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    
    public User(String name) {
        super();
        this.name = name;
    }
    
    public User() {
        
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
        if (listDisk == null) {
            this.listDisk = new ArrayList<>();
        }
        return listDisk;
    }
    
    public void setListDisk(List<Disk> listDisk) {
        this.listDisk = listDisk;
    }
    
    public void setDisk(@NotNull Disk disk) {
        disk.setMaster(this);
        getListDisk().add(disk);
    }
    public Credential getCredential() {
        if (credential == null) {
            setCredential(new Credential());
        }
        return credential;
    }
    
    public void setCredential(Credential credential) {
        if (credential == null) {
            credential = new Credential();
        }
        this.credential = credential;
        credential.setUser(this);
    }
    
}
