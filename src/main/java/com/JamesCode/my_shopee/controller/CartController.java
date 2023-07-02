package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService theCartService){
        cartService=theCartService;
    }

    @GetMapping("/add")
    public String addCart(@RequestParam("userId") int userId,@RequestParam("id") int id){
        cartService.addCart(userId,id);
        return null;
    }
}