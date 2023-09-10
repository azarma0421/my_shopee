package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SetController {

    private SettingService settingService;

    @Autowired
    public SetController(SettingService thesettingService){
        settingService = thesettingService;
    }

    @PostMapping("/SettingPage")
    public String showLoginPage(){
        return "Setting";
    }

    @GetMapping("/SettingPage/Member")
    @ResponseBody
    public <T> List<T> getMember(){
        List<T> result_list = (List<T>) settingService.get_SetMember();
        return result_list;
    }

    @GetMapping("/SettingPage/Product")
    @ResponseBody
    public <T> List<T> getProduct() {
        List<T> result_list = (List<T>) settingService.get_SetProduct();
        return result_list;
    }
}
