package com.example.demo.dao;

import com.example.demo.beans.Credential;
import com.example.demo.beans.Disk;
import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface Dao {
    public void merge(Disk disk);
    
    public void merge(User user);
    
    public void merge(TakenItem takenItem);
    
    public TakenItem getTakenItem(Long id);
    
    public Disk getDisk(Long id);
    
    public User getUser(Long id);

    
    public Credential findCredentialByname(String username);
    
    public void add(Object obj);
    
    public List<TakenItem> getAllTakenItems();
    
    public Boolean findLoginInBd(String login);
    
    public Boolean changeAccessToDisk(Long idUser, Long idDisk, boolean isFree);
    
    List<?> getAllTakenItemsOfCurrentOwner(Long id);
    
    List<?> getAllTakenItemsOfMaster(Long id);
    
    List<?> getAllTakenItemsFree();
    
    public ResponseEntity<?> addDiskToUser(Long id, Long idDisk);
    
    public List<Disk> getListDisksForUser(Long idUser);
}
