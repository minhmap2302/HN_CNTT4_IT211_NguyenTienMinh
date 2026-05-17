package org.example.b1.repository;

import org.example.b1.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @GetMapping("/hot-product")
    public ResponseEntity<List<Product>> getHotProduct(){
        List<Product> products=new ArrayList<>();
        products.add(new Product("HP001", "Áo thun 'Code is Life'", 199.000));
        products.add(new Product("HP002", "Móc khóa 'Bug Free'", 99.000));
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
