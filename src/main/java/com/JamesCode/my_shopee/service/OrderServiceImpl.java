package com.JamesCode.my_shopee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{

    private CreateDAO createDAO;

    @Autowired
    public OrderServiceImpl(CreateDAO theCreateDAO){
        createDAO = theCreateDAO;
    }

}
