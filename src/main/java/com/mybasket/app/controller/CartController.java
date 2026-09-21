package com.mybasket.app.controller;


import com.mybasket.app.dto.CartDto;
import com.mybasket.app.entity.Cart;
import com.mybasket.app.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Integer userId) {
        CartDto cartDto = cartService.getCart(userId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> addToCart(
            @PathVariable Integer userId,
            @RequestBody Map<String, Integer> requestData) {

        Long productId = Long.valueOf(requestData.get("productId"));
        int quantity = requestData.get("quantity");

        CartDto cartDto = cartService.addToCart(
                userId,
                productId,
                quantity
        );

        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }


}
