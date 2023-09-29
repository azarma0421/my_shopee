package com.JamesCode.my_shopee.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CartService {

    public List<Map<String, Object>> getCart(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> addCart(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> addToCart(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> addNumToCart(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> delCart(Map<String, Object> paramap) throws IOException;
}
