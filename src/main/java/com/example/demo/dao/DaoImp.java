package com.example.demo.dao;

import com.example.demo.beans.Credential;
import com.example.demo.beans.Disk;
import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class DaoImp extends BaseDao implements Dao {
    @PersistenceContext
    private EntityManager em;

    
    @Transactional(readOnly = true, rollbackFor = javax.persistence.NoResultException.class)
    @Override
    public Credential findCredentialByname(String username) {
        TypedQuery<Credential> list = null;
        list = em.createQuery("SELECT c from Credential c WHERE c.login = :username", Credential.class)
            .setParameter("username", username);
        return list.getSingleResult();
    }
    
    @Transactional(readOnly = true, rollbackFor = javax.persistence.NoResultException.class)
    @Override
    public List<?> getAllTakenItemsOfCurrentOwner(Long id) {
        TypedQuery<TakenItem> list = null;
        User user = getUser(id);
        list = em.createQuery("SELECT c from TakenItem c WHERE c.currentOwner = :currentOwner", TakenItem.class)
            .setParameter("currentOwner", user);
        return list.getResultList();
    }
    
    @Transactional(readOnly = true, rollbackFor = javax.persistence.NoResultException.class)
    @Override
    public List<?> getAllTakenItemsOfMaster(Long id) {
        TypedQuery<TakenItem> list = null;
        User user = getUser(id);
        list = em.createQuery("SELECT c from TakenItem c WHERE c.disk.master = :master", TakenItem.class)
            .setParameter("master", user);
        return list.getResultList();
    }
    
    @Transactional(readOnly = true, rollbackFor = javax.persistence.NoResultException.class)
    @Override
    public List<?> getAllTakenItemsFree() {
        TypedQuery<TakenItem> list = em
            .createQuery("SELECT c from TakenItem c WHERE c.isFree = :isFree", TakenItem.class)
            .setParameter("isFree", true);
        
        return list.getResultList();
    }
    
    @Override
    @Transactional
    public Boolean changeAccessToDisk(Long idUser, Long IdDisk, boolean isFree) {
        User user = getUser(idUser);
        for (Disk d : user.getListDisk()) {
            if (d.getId().equals(IdDisk)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Boolean findLoginInBd(String login) {
        Boolean result = false;
        if (findCredentialByname(login) != null) {
            result = true;
        }
        return result;
    }
    
    public Boolean userHaveDisk(User user, Long IdDisk) {
        return user.getListDisk().stream().map(Disk::getId).anyMatch(e -> e.equals(IdDisk));
    }
    
    @Transactional(readOnly = true)
    public Object nameLoginClientOwner(Long idUserOwner) {
        String name = null;
        User user = getUser(idUserOwner);
        if (user != null) {
            name = user.getCredential().getLogin();
        }
        return name;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ResponseEntity<?> addDiskToUser(Long id, Long idDisk) {
        User user = getUser(id);
        Disk disk = getDisk(idDisk);
        if (user != null && disk != null) {
            disk.setMaster(user);
            user.setDisk(disk);
            merge(user);
        }
        User result = getUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
        
    }
    
}
