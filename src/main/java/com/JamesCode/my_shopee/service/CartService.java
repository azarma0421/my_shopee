package com.JamesCode.my_shopee.service;

import com.JamesCode.my_shopee.entity.Cart;

import java.util.List;

public interface CartService {

    public List<Cart> getCart(int userId,int pid);
    public List<Cart> getmyCart(int userId,int pid);

    public void addCart(int userId,int pid);

    public void addToCart(int userId,int pid,int num);

    public void addNumToCart(int userId,int pid);

    public void delCart(int userId,int pid);
}
