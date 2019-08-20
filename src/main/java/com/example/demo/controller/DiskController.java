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
    
    @Autowired
    private DiskRepository diskRepository;
    
    // <1>
    @RequestMapping(method = RequestMethod.OPTIONS)
    ResponseEntity<?> options() {
        
        // @formatter:off
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.HEAD, HttpMethod.OPTIONS,
            HttpMethod.PUT, HttpMethod.DELETE).build();
        // @formatter:on
    }
    
    @GetMapping
    ResponseEntity<Collection<Disk>> getCollection() {
        return ResponseEntity.ok(this.diskRepository.findAll());
    }

    @PostMapping
    ResponseEntity<Disk> post(@RequestBody Disk d) { // <3>

        Disk disk = this.diskRepository.save(new Disk(d.getName()));

     URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
            .buildAndExpand(disk.getId()).toUri();
        return ResponseEntity.created(uri).body(disk);
    }
    
    // <3>
    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        return this.diskRepository.findById(id).map(d -> {
            diskRepository.delete(d);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new CustomerNotFoundException(id));
    }
    

    
}
