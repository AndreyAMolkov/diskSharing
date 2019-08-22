package com.example.demo.controller;

import com.example.demo.beans.DiskRepository;
import com.example.demo.beans.TakenItem;
import com.example.demo.beans.TakenItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/diskSharing/takenItem")
public class TakenItemController {
    
    @Autowired
    private TakenItemRepository repository;
    @Autowired
    private DiskRepository diskrepository;
    
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options() {
        
        // @formatter:off
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.HEAD, HttpMethod.OPTIONS,
            HttpMethod.PUT, HttpMethod.DELETE).build();
        // @formatter:on
    }
    
    @GetMapping
    public ResponseEntity<List<TakenItem>> getCollection() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping(value = "/{idDisk}")
    public ResponseEntity<TakenItem> post(@PathVariable Long idDisk) { // <3>
        return this.diskrepository.findById(idDisk).map(d -> {
            TakenItem takenItem = repository.save(new TakenItem(d));
            
            URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{idDisk}")
                .buildAndExpand(takenItem.getId())
                .toUri();
            return ResponseEntity.created(uri).body(takenItem);
        }).orElseThrow(() -> new CustomerNotFoundException(idDisk));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.repository.findById(id).map(d -> {
            repository.delete(d);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new CustomerNotFoundException(id));
    }
    

}
