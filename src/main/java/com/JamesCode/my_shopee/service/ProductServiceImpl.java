package com.JamesCode.my_shopee.service;

import com.JamesCode.my_shopee.dao.ProductRepository;
import com.JamesCode.my_shopee.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository theProductRepository){
        productRepository = theProductRepository;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
