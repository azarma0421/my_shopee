package com.JamesCode.my_shopee.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SettingService {

    public List<Map<String, Object>> get_SetMember(Map<String, Object> paramap) throws IOException;

    public List<Map<String, Object>> get_SetProduct() throws IOException;

    public List<Map<String, Object>> get_SetRecords();
}