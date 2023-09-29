package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.service.CommonFUNC;
import com.JamesCode.my_shopee.service.SettingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SetController {

    private SettingService settingService;

    private CommonFUNC commonFUNC;

    @Autowired
    public SetController(SettingService thesettingService,CommonFUNC thecommonFUNC){
        settingService = thesettingService;
        commonFUNC = thecommonFUNC;
    }

    @PostMapping("/SettingPage")
    public String showLoginPage(){
        return "Setting";
    }

    @GetMapping("/SettingPage/Member")
    @ResponseBody
    public List<Map<String, Object>> getMember(
            @RequestParam(value = "json", required = false) String jsonParam)
            throws IOException {

        Map<String, Object> jsonData = commonFUNC.json2Map(jsonParam);

        List<Map<String, Object>> result_list = settingService.get_SetMember(jsonData);
        System.out.println(result_list);
        return result_list;
    }

    @GetMapping("/SettingPage/Product")
    @ResponseBody
    public List<Map<String, Object>> getProduct() throws IOException {
        List<Map<String, Object>> result_list = settingService.get_SetProduct();
        return result_list;
    }
}
