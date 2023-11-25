package com.JamesCode.my_shopee.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LoginService {

    public String sign_up(Map<String, Object> paramap) throws IOException;
    public String getPW(Map<String, Object> paramap) throws IOException;
}
