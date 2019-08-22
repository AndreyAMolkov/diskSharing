package com.example.demo.controller;

import com.example.demo.beans.Disk;
import com.example.demo.beans.DiskRepository;
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
import java.util.Collection;

@RestController
@RequestMapping("/v1/diskSharing/disk")
public class DiskController {
    
    @Autowired(required = true)
    private DiskRepository diskRepository;
    
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options() {
        
        // @formatter:off
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE).build();
        // @formatter:on
    }
    
    @GetMapping
    public ResponseEntity<Collection<Disk>> getCollection() {
        return ResponseEntity.ok(this.diskRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Disk> post(@RequestBody Disk d) { // <3>

        Disk disk = this.diskRepository.save(new Disk(d.getName()));

     URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
            .buildAndExpand(disk.getId()).toUri();
        return ResponseEntity.created(uri).body(disk);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.diskRepository.findById(id).map(d -> {
            diskRepository.delete(d);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new CustomerNotFoundException(id));
    }
    

    
}
