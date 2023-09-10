package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.entity.User;
import com.JamesCode.my_shopee.service.UserService;
import com.JamesCode.my_shopee.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService theUserService){
        userService = theUserService;
    }

    @GetMapping("/users")
    public List<UserServiceImpl.DulUser> findAlls(){
//        return userService.getUsersWithCarts();
        List<UserServiceImpl.DulUser> new1 = userService.getUsersWithCarts();
        return new1;
    }
}
