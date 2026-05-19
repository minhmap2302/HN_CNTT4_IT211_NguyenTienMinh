package org.example.ptit_cntt4_it211_session04.controller;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    static class Order {

        private String orderId;
        private String customerName;
        private String address;
        private String productId;
        private int quantity;

        public Order() {
        }

        public Order(String orderId, String customerName, String address, String productId, int quantity) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.address = address;
            this.productId = productId;
            this.quantity = quantity;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    @PostMapping
    public Order createOrder(@RequestBody Order orderRequest) {

        String generatedOrderId = UUID.randomUUID().toString();

        Order createdOrder = new Order(
                generatedOrderId,
                orderRequest.getCustomerName(),
                orderRequest.getAddress(),
                orderRequest.getProductId(),
                orderRequest.getQuantity()
        );

        return createdOrder;
    }
}