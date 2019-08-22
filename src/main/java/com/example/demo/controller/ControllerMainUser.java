package com.example.demo.controller;

import com.example.demo.beans.Disk;
import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import com.example.demo.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;

@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RestController
@RequestMapping("/user/diskSharing")
public class ControllerMainUser {
    
    private Long idPrincipal;
    @Autowired
    private Dao dao;
    
    @RequestMapping(method = RequestMethod.OPTIONS)
    ResponseEntity<?> options() {
        
        // @formatter:off
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE).build();
        // @formatter:on
    }
    
    @GetMapping(value = "/welcome")
    public ResponseEntity<?> welcome() {
        updateDatePrincipal();
        User user = dao.getUser(idPrincipal);
        return ResponseEntity.ok("Добро пожаловать " + user.getName());
    }
    @GetMapping(value = "/all/takenItem")
    public ResponseEntity<List<TakenItem>> getAllCollectionTakenItems() {
        return ResponseEntity.ok(dao.getAllTakenItems());
    }
    
    @GetMapping(value = "/currentOwner")
    public List<?> getAllTakenItemsOfCurrentOwner() {
        return dao.getAllTakenItemsOfCurrentOwner(idPrincipal);
        
    }
    
    @GetMapping(value = "/currentOwner/free")
    public List<?> getAllTakenItemsFree() {
        return dao.getAllTakenItemsFree();
        
    }
    
    @GetMapping(value = "/master")
    public List<?> getAllTakenItemsOfMaster() {
        return dao.getAllTakenItemsOfMaster(idPrincipal);
        
    }
    
    @GetMapping(value = "/user/disks")
    public ResponseEntity<?> getListDisksForUser() {
        List<Disk> list = dao.getListDisksForUser(idPrincipal);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    private void updateDatePrincipal() {
        String userName = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Authentication principal = SecurityContextHolder.getContext().getAuthentication();
            userName = principal.getName();
            try {
                this.idPrincipal = dao.findCredentialByname(userName).getIdClient();
            } catch (CannotCreateTransactionException e) {
                // place for log
            }
        }
        // place for log
    }
}
