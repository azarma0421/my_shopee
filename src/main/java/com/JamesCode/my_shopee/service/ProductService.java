package com.JamesCode.my_shopee.service;


import com.JamesCode.my_shopee.entity.Product;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductService {

    //    old
//    public List<Product> getProducts();

    public List<Map<String, Object>> getProducts(Map<String, Object> paramap) throws IOException;

//    old
    //    public List<ProductServiceImpl.Cart_Detail> getCart_Detail(int id);

    public List<Map<String, Object>> getCart_Detail(Map<String, Object> paramap) throws IOException;
}
