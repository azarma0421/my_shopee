package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.entity.Product;
import com.JamesCode.my_shopee.entity.User;
import com.JamesCode.my_shopee.service.ProductService;
import com.JamesCode.my_shopee.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
        List<Product> new1 = productService.getProducts();
        model.addAttribute("products", new1);

        List<ProductServiceImpl.Cart_Detail> new2 = productService.getCart_Detail();
        model.addAttribute("Cart", new2);
        return "Home";
    }
}
