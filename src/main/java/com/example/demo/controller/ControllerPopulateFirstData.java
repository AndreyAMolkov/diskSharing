package com.example.demo.controller;

import com.example.demo.beans.Credential;
import com.example.demo.beans.Disk;
import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import com.example.demo.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import java.util.Collections;

@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RestController
@RequestMapping("/start")
public class ControllerPopulateFirstData {
    
    @Autowired
    private Dao dao;
    // <1>
    @RequestMapping(method = RequestMethod.OPTIONS)
    ResponseEntity<?> options() {
        
        // @formatter:off
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.HEAD, HttpMethod.OPTIONS,
            HttpMethod.PUT, HttpMethod.DELETE).build();
        // @formatter:on
    }
    

    @GetMapping
    public ResponseEntity<?> start() {
        Disk d = new Disk("Disk1");
        Disk d2 = new Disk("Disk2");
        Disk d3 = new Disk("Disk3");
        User admin = new User("Admin");
        User user = new User("User");
        Credential crAdmin = new Credential("admin", "admin", "admin");
        Credential crUser = new Credential("user", "user", "user");
        admin.setDisk(d);
        admin.setCredential(crAdmin);
        user.setDisk(d2);
        user.setCredential(crUser);
        d.setCurrentOwner(user);
        TakenItem takenItem = new TakenItem();
        TakenItem takenItem2 = new TakenItem();
        
        takenItem.setDisk(d);
        takenItem2.setDisk(d3);
        takenItem.setCurrentOwner(user);
        dao.add(user);
        
        dao.add(admin);
        dao.add(d3);
        dao.add(takenItem);
        dao.add(takenItem2);
        return ResponseEntity.ok(Collections.EMPTY_LIST);
    }
    

}
