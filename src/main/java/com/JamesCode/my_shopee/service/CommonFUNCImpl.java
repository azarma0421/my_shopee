package com.JamesCode.my_shopee.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommonFUNCImpl implements CommonFUNC{

    // 將前端收到的json組成map
    @Override
    public Map<String, Object> json2Map(String jsonParam) {

        if(jsonParam == null || jsonParam == ""){
            System.out.println("jsonParam is null");
            return null;
        }
        System.out.println("jsonParam: " + jsonParam);

        Map<String, Object> jsonData = new HashMap<>();
        try {
            // URL-decode the JSON data
            String decodedJson = java.net.URLDecoder.decode(jsonParam, "UTF-8");

            // Parse the JSON string into a Map
            ObjectMapper objectMapper = new ObjectMapper();
            jsonData = objectMapper.readValue(decodedJson, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error parsing JSON");
        }
        return jsonData;
    }
}
