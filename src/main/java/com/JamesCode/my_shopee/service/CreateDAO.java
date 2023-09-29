package com.JamesCode.my_shopee.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CreateDAO {

    List<Map<String, Object>> createDAOImpl(String namespace, Map<String, Object> paramMap) throws IOException;
}
