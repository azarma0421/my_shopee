package com.JamesCode.my_shopee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService{

    private CreateDAO createDAO;

    @Autowired
    public LoginServiceImpl(CreateDAO theCreateDAO){
        createDAO = theCreateDAO;
    }

    @Override
    public String sign_up(Map<String, Object> paraMap) throws IOException {

        List<Map<String, Object>> result_list = new ArrayList<>();
        String msg = "";

        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("LoginMapper.checkAC",paraMap);
        if(result_list.size() != 0){
            msg += "Username already in use.\n";
        }
        createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("LoginMapper.checkPW",paraMap);
        if(result_list.size() != 0){
            msg += "Password already in use.\n";
        }
        createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("LoginMapper.checkmail",paraMap);
        if(result_list.size() != 0){
            msg += "Mail already in use.\n";
        }

        if(!"".equals(msg)) return msg;

        createDAO = new createDAOImpl();
        createDAO.createDAOImpl("LoginMapper.sign_up",paraMap);

        createDAO = new createDAOImpl();
        createDAO.createDAOImpl("LoginMapper.add_auth",paraMap);

        msg = "Sign up success!";
        return msg;
    }

    @Override
    public String getPW(Map<String, Object> paraMap) throws IOException {

        List<Map<String, Object>> result_list = new ArrayList<>();
        String msg = "";

        CreateDAO createDAO = new createDAOImpl();
        result_list = createDAO.createDAOImpl("LoginMapper.getPW",paraMap);
        if(result_list.size() == 0){
            msg = "The username doesn't excise or the mail is wrong. \n";
        }else {
            String pw = result_list.get(0).get("PW").toString();
            msg = "The Password is \n" + pw;
        }

        return msg;
    }
}
