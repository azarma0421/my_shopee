package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.service.CartService;
import com.JamesCode.my_shopee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CartController {

    private CartService cartService;
    private ProductService productService;

    @Autowired
    public CartController(CartService theCartService, ProductService theProductService){
        cartService=theCartService;
        productService = theProductService;
    }

    @GetMapping("/cart")
    public List<Map<String, Object>> getCart(@RequestParam("userId") int userId,
                          @RequestParam(value = "pid",required = false) Integer pid) throws IOException {

        if (pid == null) {
            pid = 0;
        }
        pid = pid.intValue();

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pid",pid);
        paramMap.put("userId",userId);

        List<Map<String, Object>> result_list = cartService.getCart(paramMap);
        return result_list;
    }

    @PutMapping("/cart")
    public List<Map<String, Object>> addCart(@RequestParam("userId") int userId,@RequestParam("pid") int pid) throws IOException {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userId",userId);
        paramMap.put("pid",pid);
        List<Map<String, Object>> result_list = cartService.addCart(paramMap);
        return result_list;
    }

    @DeleteMapping("/cart")
    public String delCart(@RequestParam("userId") int userId,@RequestParam("pid") int pid) throws IOException {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userId",userId);
        paramMap.put("pid",pid);
        cartService.delCart(paramMap);
        return null;
    }

    @GetMapping("/cartdetail")
    public List<Map<String, Object>> refreshCart(@RequestParam("userId") int userId) throws IOException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId",userId);
        List<Map<String, Object>> result_list = productService.getCart_Detail(paramMap);
        return result_list;
    }
}
