package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.service.ProductService;
import com.JamesCode.my_shopee.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    private ProductService productService;

    private UserService userService;

    @Autowired
    public LoginController(ProductService productService, UserService userService){
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/LoginPage")
    public String showLoginPage(){
        return "fancy-login";
    }

    @GetMapping("/")
    public String showhome(Model model, HttpSession session) throws IOException {
        String username = (String) session.getAttribute("username");
        String ROLE = (String) session.getAttribute("ROLE");
        model.addAttribute("username", username);
        System.out.println("[DEBUG] Login username: " + username);
        System.out.println("[DEBUG] Login role: " + ROLE);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username",username);
        List<Map<String, Object>> result_list = userService.getUserProfile(paramMap);

        String uid = result_list.get(0).get("id").toString();
        // 商品全撈
        List<Map<String, Object>> products = productService.getProducts(paramMap);
        model.addAttribute("products", products);

        paramMap.put("userId",uid);
        result_list = productService.getCart_Detail(paramMap);
        if (result_list.size() == 0) {
            result_list = null;
        }

        System.out.println("Cart result_list: "+result_list);
        model.addAttribute("Cart", result_list);
        model.addAttribute("ROLE", ROLE);
        model.addAttribute("UserId", uid);
        return "Home";
    }
}
