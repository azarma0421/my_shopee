package com.JamesCode.my_shopee.service;

import com.JamesCode.my_shopee.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(int theId);

    public void save(User theUser);

    public void deleteById(int theId);
}
