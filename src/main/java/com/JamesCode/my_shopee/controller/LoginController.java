package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.entity.Product;
import com.JamesCode.my_shopee.entity.User;
import com.JamesCode.my_shopee.service.ProductService;
import com.JamesCode.my_shopee.service.ProductServiceImpl;
import com.JamesCode.my_shopee.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.JamesCode.my_shopee.controller.CartController.logger;

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
        return "plain-login";
    }

    @GetMapping("/")
    public String showhome(Model model, HttpSession session){
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        logger.info("[DEBUG] Login username: " + username);

        List<User> userinfo = userService.getUserProfile(username);
        int id = userinfo.get(0).getId();

        // 商品全撈
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);

        List<ProductServiceImpl.Cart_Detail> cart = productService.getCart_Detail(id);
        if (cart.size() == 0) {
            cart = null;
        }
        model.addAttribute("Cart", cart);

        return "Home";
    }
}
