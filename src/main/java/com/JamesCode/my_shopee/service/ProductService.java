package com.JamesCode.my_shopee.service;


import com.JamesCode.my_shopee.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();

    public List<ProductServiceImpl.Cart_Detail> getCart_Detail();
}
