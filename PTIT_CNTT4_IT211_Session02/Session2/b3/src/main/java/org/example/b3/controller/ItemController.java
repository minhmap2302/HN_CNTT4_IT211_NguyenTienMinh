package org.example.b3.controller;

import org.example.b3.entity.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private List<Item> items = new ArrayList<>();

    private AtomicLong counter = new AtomicLong(1);
    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item item1=new Item();
        item1.setId(counter.getAndIncrement());
        item1.setName(item.getName());
        item1.setPrice(item.getPrice());
        items.add(item1);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id,@RequestBody Item item) {
        Optional<Item> item1 = items.stream()
                .filter(e -> e.getId().equals(id))
                .map(e -> {
                    e.setName(item.getName());
                    e.setPrice(item.getPrice());
                    return e;
                })
                .findFirst();
        if(item1.isPresent()) {
            return ResponseEntity.ok(item1.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id){

        Optional<Item> existingItem = items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();

        if(existingItem.isPresent()){

            items.remove(existingItem.get());

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id){
        Optional<Item> existingItem = items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();

        if(existingItem.isPresent()){
            return ResponseEntity.ok(existingItem.get());
        }
        return ResponseEntity.notFound().build();
    }


}
