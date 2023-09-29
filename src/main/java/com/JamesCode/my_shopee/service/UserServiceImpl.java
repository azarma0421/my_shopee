package com.JamesCode.my_shopee.service;

import com.JamesCode.my_shopee.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private CreateDAO createDAO;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository,CreateDAO theCreateDAO) {
        userRepository = theUserRepository;
        createDAO = theCreateDAO;
    }

    @Override
    public List<Map<String, Object>> getUserProfile(Map<String, Object> paramap) throws IOException {
        List<Map<String, Object>> result_list = new ArrayList<>();
        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("HomePageMapper.getUserProfile",paramap);
        return result_list;
    }
}
