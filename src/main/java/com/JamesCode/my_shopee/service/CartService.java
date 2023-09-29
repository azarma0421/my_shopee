package com.JamesCode.my_shopee.service;

import com.JamesCode.my_shopee.entity.Cart;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CartService {

//    public List<Cart> getCart(int userId,int pid);
//    public List<Cart> getmyCart(int userId,int pid);

//    public void addCart(int userId,int pid);

//    public void addToCart(int userId,int pid,int num);
//
//    public void addNumToCart(int userId,int pid);
//
//    public void delCart(int userId,int pid);

    public List<Map<String, Object>> getCart(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> addCart(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> addToCart(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> addNumToCart(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> delCart(Map<String, Object> paramap) throws IOException;
}
