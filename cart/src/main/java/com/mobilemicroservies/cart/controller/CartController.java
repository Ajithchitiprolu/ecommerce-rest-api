package com.mobilemicroservies.cart.controller;

import com.mobilemicroservies.cart.dto.MobileRequest;
import com.mobilemicroservies.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody MobileRequest mobileRequest){
        Boolean added = cartService.addToCart(mobileRequest);

        return added ? new ResponseEntity<>("Added Successfully", HttpStatus.OK) : new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
}
