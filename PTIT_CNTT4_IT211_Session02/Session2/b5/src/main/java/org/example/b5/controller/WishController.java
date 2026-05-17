package org.example.b5.controller;

import org.example.b5.entity.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/wishes")
public class WishController {
    private int wishesLeft = 3;

    private List<String> history = new ArrayList<>();

    private List<Item> items = new ArrayList<>();
    private boolean canWish() {
        return wishesLeft > 0;
    }
    @PostMapping("/item")
    public ResponseEntity<?> createItem(
            @RequestBody Item item) {

        if (!canWish()) {

            history.add("Rejected: out of wishes");

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Thần đèn đã hết lượt ước!");
        }

        if (item.getName() == null ||
                item.getName().isBlank()) {

            history.add("Rejected: invalid item");

            return ResponseEntity
                    .badRequest()
                    .body("Tên item không hợp lệ!");
        }

        item.setId(items.size() + 1);

        item.setStatus("CREATED");

        items.add(item);

        wishesLeft--;

        history.add("Created item: " + item.getName());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(item);
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<?> updateItem(
            @PathVariable int id) {

        if (!canWish()) {

            history.add("Rejected: out of wishes");

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Thần đèn đã hết lượt ước!");
        }

        for (Item item : items) {

            if (item.getId() == id) {

                item.setStatus("MAGIC_UPDATED");

                wishesLeft--;

                history.add("Updated item id: " + id);

                return ResponseEntity.ok(item);
            }
        }

        history.add("Item not found");

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Không tìm thấy item!");
    }

    @GetMapping("/random")
    public ResponseEntity<?> randomWish() {

        if (!canWish()) {

            history.add("Rejected: out of wishes");

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Thần đèn đã hết lượt ước!");
        }

        wishesLeft--;

        history.add("Random wish used");

        return ResponseEntity.ok(
                "Bạn sẽ gặp may mắn hôm nay!"
        );
    }

    @GetMapping("/history")
    public ResponseEntity<List<String>> getHistory() {

        return ResponseEntity.ok(history);
    }
}
