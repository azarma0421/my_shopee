package com.JamesCode.my_shopee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{

    private CreateDAO createDAO;

    @Autowired
    public ProductServiceImpl(CreateDAO theCreateDAO){
        createDAO = theCreateDAO;
    }

    @Override
    public List<Map<String, Object>> getProducts(Map<String, Object> paraMap) throws IOException {

        List<Map<String, Object>> result_list = new ArrayList<>();
        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("HomePageMapper.getProducts",paraMap);
        return result_list;
    }

    @Override
    public List<Map<String, Object>> getCart_Detail(Map<String, Object> paraMap) throws IOException {

        List<Map<String, Object>> result_list = new ArrayList<>();
        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("HomePageMapper.getCart_Detail",paraMap);
        return result_list;
    }
}
