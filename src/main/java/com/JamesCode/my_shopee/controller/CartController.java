package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.entity.Cart;
import com.JamesCode.my_shopee.service.CartService;
import com.JamesCode.my_shopee.service.ProductService;
import com.JamesCode.my_shopee.service.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    private CartService cartService;
    private ProductService productService;

    static final Logger logger = LogManager.getLogger(CartController.class);


    @Autowired
    public CartController(CartService theCartService, ProductService theProductService){
        cartService=theCartService;
        productService = theProductService;
    }

    @GetMapping("/cart")
    public List<Cart> getCart(@RequestParam("userId") int userId,
                          @RequestParam(value = "pid",required = false) Integer pid){

        if (pid == null) {
            pid = 0;
        }
        pid = pid.intValue();
        List<Cart> cartItem = cartService.getCart(userId,pid);
        System.out.println("cartItem: " + cartItem);
//        cartService.getCart(userId);
        return cartItem;
    }

    @PutMapping("/cart")
    public String addCart(@RequestParam("userId") int userId,@RequestParam("pid") int pid){
        cartService.addCart(userId,pid);
        return null;
    }

    @DeleteMapping("/cart")
    public String delCart(@RequestParam("userId") int userId,@RequestParam("pid") int pid){
        cartService.delCart(userId,pid);
        return null;
    }

    @GetMapping("/cartdetail")
    public List<ProductServiceImpl.Cart_Detail> refreshCart(@RequestParam("userId") int userId){
        List<ProductServiceImpl.Cart_Detail> cartDetail = productService.getCart_Detail(userId);
        return cartDetail;
    }
}
