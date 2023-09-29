package com.JamesCode.my_shopee.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class createDAOImpl implements CreateDAO {

    String DAO = "com.JamesCode.my_shopee.mapper.";

    @Override
    public List<Map<String, Object>> createDAOImpl(String s, Map<String, Object> paramMap) throws IOException {

        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");

        //2.通過配置訊息取得一個SqlSessionFactory工廠對象
        SqlSessionFactory fac =	new SqlSessionFactoryBuilder().build( in );

        //3.通過工廠取得一個SqlSession對象
        SqlSession session = fac.openSession();

        DAO += s;

        //4.連接對應sql語句
        List<Map<String, Object>> resultList = session.selectList(DAO,paramMap);

        session.commit();
        session.close();

        return resultList;
    }



}
