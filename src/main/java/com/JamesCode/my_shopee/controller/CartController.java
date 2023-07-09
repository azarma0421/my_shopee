package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.entity.Cart;
import com.JamesCode.my_shopee.service.CartService;
import com.JamesCode.my_shopee.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService theCartService){
        cartService=theCartService;
    }

    @GetMapping("/get")
    public List<Cart> getCart(@RequestParam("userId") int userId,
                          @RequestParam(value = "pid",required = false) Integer pid){
        if (pid == null) {
            pid = 0;
        }
        pid = pid.intValue();
        List<Cart> new2 = cartService.getCart(userId,pid);
        System.out.println("new2: " + new2);
//        cartService.getCart(userId);
        return new2;
    }

    @PutMapping("/add")
    public String addCart(@RequestParam("userId") int userId,@RequestParam("id") int id){
        cartService.addCart(userId,id);
        return null;
    }

    @DeleteMapping("/del")
    public String delCart(@RequestParam("userId") int userId,@RequestParam("id") int id){
        cartService.delCart(userId,id);
        return null;
    }
}
