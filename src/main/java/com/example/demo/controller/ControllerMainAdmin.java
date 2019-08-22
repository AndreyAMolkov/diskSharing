package com.example.demo.controller;

import com.example.demo.beans.TakenItem;
import com.example.demo.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;

@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RestController
@RequestMapping("/admin/diskSharing")
public class ControllerMainAdmin {
    
    @Autowired
    private Dao dao;
    
    @RequestMapping(method = RequestMethod.OPTIONS)
    ResponseEntity<?> options() {
        
        // @formatter:off
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE).build();
        // @formatter:on
    }
    
    @GetMapping(value = "/all/takenItem")
    public ResponseEntity<List<TakenItem>> getAllCollectionTakenItems() {
        return ResponseEntity.ok(dao.getAllTakenItems());
    }
    
    @GetMapping(value = "/user/{id}/disk/{idDisk}")
    public ResponseEntity<?> addDiskToUser(@PathVariable Long id, @PathVariable Long idDisk) {
        
        return dao.addDiskToUser(id, idDisk);
    }
    
    @GetMapping(value = "/currentOwner/{id}")
    public List<?> getAllTakenItemsOfCurrentOwner(@PathVariable Long id) {
        return dao.getAllTakenItemsOfCurrentOwner(id);
        
    }
    
    @GetMapping(value = "/currentOwner/free")
    public List<?> getAllTakenItemsFree() {
        return dao.getAllTakenItemsFree();
        
    }
    
    @GetMapping(value = "/master/{id}")
    public List<?> getAllTakenItemsOfMaster(@PathVariable Long id) {
        return dao.getAllTakenItemsOfMaster(id);
        
    }
    
    @GetMapping(value = "/user/{id}/disks")
    public ResponseEntity<?> getListDisks(@PathVariable Long id) {
        return new ResponseEntity<>(dao.getListDisksForUser(id), HttpStatus.OK);
    }
}
