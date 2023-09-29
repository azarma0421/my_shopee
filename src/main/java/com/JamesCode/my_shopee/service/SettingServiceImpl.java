package com.JamesCode.my_shopee.service;

import com.JamesCode.my_shopee.dao.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SettingServiceImpl implements SettingService {

    private SettingRepository settingRepository;

    private CreateDAO createDAO;

    @Autowired
    public SettingServiceImpl( SettingRepository theRepository,CreateDAO theCreateDAO ){
        settingRepository = theRepository;
        createDAO = theCreateDAO;
    }

    @Override
    public List<Map<String, Object>> get_SetMember(Map<String, Object> paraMap) throws IOException {

        List<Map<String, Object>> result_list = new ArrayList<>();
        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("SettingMapper.getMember",paraMap);
        return result_list;
    }

    @Override
    public List<Map<String, Object>> get_SetProduct() throws IOException {

        List<Map<String,Object>> result_list = new ArrayList<>();
        Map<String,Object> paraMap = new HashMap<>();
        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("SettingMapper.getProduct",paraMap);
        return result_list;
    }

    @Override
    public List<Map<String, Object>> get_SetRecords() {
        return null;
    }
}
