package com.example.demo.dao;

import com.example.demo.beans.Disk;
import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDao implements Dao {
    @PersistenceContext
    private EntityManager em;
    
    public EntityManager getEntityManager() {
        return em;
    }
    
    public BaseDao() {
        
    }
    
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public TakenItem getTakenItem(Long id) {
        return em.find(TakenItem.class, id);
    }
    
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Disk getDisk(Long id) {
        return em.find(Disk.class, id);
    }
    
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public User getUser(Long id) {
        return em.find(User.class, id);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public java.util.List<TakenItem> getAllTakenItems() {
        return em.createQuery("SELECT c FROM TakenItem c").getResultList();
        
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void merge(User user) {
        em.merge(user);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void merge(TakenItem takenItem) {
        em.merge(takenItem);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void merge(Disk disk) {
        em.merge(disk);
    }
    
    @Override
    @Transactional
    public void add(Object obj) {
        em.persist(obj);
    }
    
    @Transactional
    public void removeTakenItem(Long idTakenItem) {
        TakenItem t = getTakenItem(idTakenItem);
        em.remove(t);
    }
    
}
