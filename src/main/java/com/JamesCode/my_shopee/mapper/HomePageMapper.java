package com.JamesCode.my_shopee.mapper;

import java.util.List;
import java.util.Map;

public interface HomePageMapper {

    List<Map<String, Object>> getUserProfile();

    List<Map<String, Object>> getProducts();

    List<Map<String, Object>> getCart_Detail();

    List<Map<String, Object>> getCartByUid();

    List<Map<String, Object>> addToCart();

    List<Map<String, Object>> addNumToCart();

    List<Map<String, Object>> delCart();
}
