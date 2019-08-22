package com.example.demo.controller;

import com.example.demo.beans.Disk;
import com.example.demo.beans.User;
import com.example.demo.beans.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/diskSharing/user")
public class UserController {
    
    @Autowired
    private UserRepository repository;
    
    public @RequestMapping(method = RequestMethod.OPTIONS)
    ResponseEntity<?> options() {
        
        // @formatter:off
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE).build();
        // @formatter:on
    }
    
    @GetMapping
    public ResponseEntity<List<User>> getCollection() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @PostMapping
    public ResponseEntity<User> post(@RequestBody Disk d) { // <3>

        User user = this.repository.save(new User(d.getName()));

     URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
            .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
    
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.repository.findById(id).map(d -> {
            repository.delete(d);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new CustomerNotFoundException(id));
    }
    

}
