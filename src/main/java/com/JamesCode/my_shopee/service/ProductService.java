package com.JamesCode.my_shopee.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductService {

    public List<Map<String, Object>> getProducts(Map<String, Object> paramap) throws IOException;

    public List<Map<String, Object>> getCart_Detail(Map<String, Object> paramap) throws IOException;
}
