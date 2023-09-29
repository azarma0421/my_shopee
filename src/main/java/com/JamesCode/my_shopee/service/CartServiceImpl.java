package com.JamesCode.my_shopee.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CartServiceImpl implements CartService{

    private CreateDAO createDAO;
    @Autowired
    public CartServiceImpl(CreateDAO theCreateDAO){
        createDAO = theCreateDAO;
    }

    @Override
    public List<Map<String, Object>> getCart(Map<String, Object> paraMap) throws IOException {

        List<Map<String, Object>> result_list = new ArrayList<>();
        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("HomePageMapper.getCartByUid",paraMap);
        return result_list;
    }

    //add cart by userId,pid
    @Override
    public List<Map<String, Object>> addCart(Map<String, Object> paraMap) throws IOException {

        List<Map<String, Object>> result_list = new ArrayList<>();

        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("HomePageMapper.getCartByUid",paraMap);
        try{
            if (result_list.isEmpty()) {
                System.out.println("[INFO] The list is empty.");
                paraMap.put("num","1");
                addToCart(paraMap);

            } else {
                System.out.println("[INFO] The list is not empty.");
                addNumToCart(paraMap);
            }
            System.out.println("[INFO] Already add to cart.");
        }catch (Exception e){
            System.out.println(e);
            System.out.println("[INFO] Fail to add to cart.");
        }
        return result_list;
    }

    // new product for cart
    @Override
    public List<Map<String, Object>> addToCart(Map<String, Object> paraMap) throws IOException {

        List<Map<String, Object>> result_list = new ArrayList<>();

        CreateDAO createDAO = new createDAOImpl();
        createDAO.createDAOImpl("HomePageMapper.addToCart",paraMap);
        return null;
    }

    // already excise in cart
    @Override
    public List<Map<String, Object>> addNumToCart(Map<String, Object> paraMap) throws IOException {

        List<Map<String, Object>> result_list = new ArrayList<>();

        CreateDAO createDAO = new createDAOImpl();
        createDAO.createDAOImpl("HomePageMapper.addNumToCart",paraMap);
        return null;
    }


    @Override
    public List<Map<String, Object>> delCart(Map<String, Object> paraMap) throws IOException {

        List<Map<String, Object>> result_list = new ArrayList<>();

        CreateDAO createDAO = new createDAOImpl();
        createDAO.createDAOImpl("HomePageMapper.delCart",paraMap);
        return null;
    }
}
