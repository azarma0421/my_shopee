package com.JamesCode.my_shopee.service;

import com.JamesCode.my_shopee.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {

    public List<Map<String, Object>> getUserProfile(Map<String, Object> paramap) throws IOException;
}

