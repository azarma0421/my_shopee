package com.JamesCode.my_shopee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SettingServiceImpl implements SettingService {

    private CreateDAO createDAO;

    @Autowired
    public SettingServiceImpl( CreateDAO theCreateDAO ){
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
    public List<Map<String, Object>> update_SetMember(Map<String, Object> paraMap) throws IOException {

        List<Map<String, Object>> result_list = new ArrayList<>();

        String pw = paraMap.get("PW").toString();
        if(!"".equals(pw)){
            paraMap.put("PW", "{noop}" + paraMap.get("PW").toString() );
        }
        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("SettingMapper.update_user",paraMap);
        return result_list;
    }

    @Override
    public List<Map<String, Object>> get_SetProduct(Map<String, Object> paraMap) throws IOException {

        List<Map<String,Object>> result_list = new ArrayList<>();
        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("SettingMapper.getProduct",paraMap);
        return result_list;
    }

    @Override
    public List<Map<String, Object>> get_ColData(Map<String, Object> paraMap) throws IOException {

        List<Map<String,Object>> result_list = new ArrayList<>();
        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("SettingMapper.getProduct",paraMap);
        return result_list;
    }

    @Override
    public List<Map<String, Object>> get_SetRecords() {
        return null;
    }

    @Override
    public List<Map<String, Object>> add_SetProduct(Map<String, Object> paraMap) throws IOException {

        String src = "/images";

        List<Map<String,Object>> result_list = new ArrayList<>();
        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("SettingMapper.getJPGName",paraMap);
        String name = result_list.get(0).get("name").toString();

        src += "/" + paraMap.get("Category").toString();
        src += "/" + name + ".jpg";

        paraMap.put("src",src);

        createDAO = new createDAOImpl();
        createDAO.createDAOImpl("SettingMapper.addProduct",paraMap);
        return result_list;
    }

    @Override
    public List<Map<String, Object>> update_SetProduct(Map<String, Object> paraMap) throws IOException {

        CreateDAO createDAO = new createDAOImpl();
        createDAO.createDAOImpl("SettingMapper.editProduct",paraMap);
        return null;
    }

    @Override
    public List<Map<String, Object>> del_SetProduct(Map<String, Object> paraMap) throws IOException {

        List<Map<String,Object>> result_list = new ArrayList<>();
        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("SettingMapper.getProduct",paraMap);

        CreateDAO createDAO1 = new createDAOImpl();
        createDAO1.createDAOImpl("SettingMapper.delProduct",paraMap);

        return result_list;
    }
}
