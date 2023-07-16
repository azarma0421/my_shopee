package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.entity.Product;
import com.JamesCode.my_shopee.service.ProductService;
import com.JamesCode.my_shopee.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    private ProductService productService;

    @Autowired
    public LoginController(ProductService theProductService){
        productService = theProductService;
    }

    @GetMapping("/")
    public String showhome(Model model){
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);

        //test
        int id =1;

        List<ProductServiceImpl.Cart_Detail> cart = productService.getCart_Detail(id);
        if(cart.size()==0){
            cart=null;
        }
        model.addAttribute("Cart", cart);
        return "Home";
    }
}
