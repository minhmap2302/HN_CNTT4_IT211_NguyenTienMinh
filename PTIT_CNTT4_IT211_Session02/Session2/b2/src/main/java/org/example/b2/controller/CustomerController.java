package org.example.b2.controller;

import org.example.b2.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    private AtomicLong counter = new AtomicLong(1);

    @PostMapping
    public ResponseEntity<Customer> addCustomer(
            @RequestBody Customer customer) {

        customer.setId(counter.getAndIncrement());

        customers.add(customer);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        Optional<Customer> existingCustomer = customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (existingCustomer.isPresent()) {

            Customer c = existingCustomer.get();

            c.setName(customer.getName());
            c.setEmail(customer.getEmail());

            return ResponseEntity.ok(c);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable Long id) {

        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}