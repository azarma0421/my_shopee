package com.JamesCode.my_shopee.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SettingService {

    public List<Map<String, Object>> get_SetMember(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> get_SetProduct(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> get_SetRecords(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> update_SetR_status(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> get_ColData(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> update_SetMember(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> add_SetProduct(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> update_SetProduct(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> del_SetProduct(Map<String, Object> paramap) throws IOException;
    public List<Map<String, Object>> get_Detail(Map<String, Object> paramap) throws IOException;

    
}