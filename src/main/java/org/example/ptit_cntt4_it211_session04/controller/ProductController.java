package org.example.ptit_cntt4_it211_session04.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private Map<String, Product> productStore = new HashMap<>();

    public ProductController() {
        productStore.put("P001", new Product("P001", "Laptop Dell", 15000000, 10));
        productStore.put("P002", new Product("P002", "Mouse Logitech", 350000, 50));
        productStore.put("P003", new Product("P003", "Keyboard Akko", 1200000, 20));
    }

    static class Product {

        private String productId;
        private String productName;
        private double price;
        private int quantity;

        public Product() {
        }

        public Product(String productId, String productName, double price, int quantity) {
            this.productId = productId;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    @GetMapping
    public Collection<Product> getAllProducts() {
        return productStore.values();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productStore.put(product.getProductId(), product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(
            @PathVariable String productId,
            @RequestBody Product productRequest
    ) {
        if (!productStore.containsKey(productId)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy sản phẩm có ID: " + productId);
        }

        Product updatedProduct = new Product(
                productId,
                productRequest.getProductName(),
                productRequest.getPrice(),
                productRequest.getQuantity()
        );

        productStore.put(productId, updatedProduct);

        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        if (!productStore.containsKey(productId)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy sản phẩm có ID: " + productId);
        }

        productStore.remove(productId);

        return ResponseEntity.ok("Đã xóa sản phẩm có ID: " + productId);
    }
}