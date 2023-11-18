package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.service.CommonFUNC;
import com.JamesCode.my_shopee.service.OrderService;
import com.JamesCode.my_shopee.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    private SettingService settingService;

    private CommonFUNC commonFUNC;

    @Autowired
    public OrderController(SettingService thesettingService, CommonFUNC thecommonFUNC){
        settingService = thesettingService;
        commonFUNC = thecommonFUNC;
    }

    @PostMapping("/OrdersPage")
    public String showPage(Model model,
           @RequestParam(value = "uid") String uid)
            throws IOException {
        model.addAttribute("UserId", uid);
        return "Orders";
    }

    @GetMapping("/OrdersPage")
    @ResponseBody
    public List<Map<String, Object>> getOrders(
            @RequestParam(value = "json", required = false) String jsonParam)
            throws IOException {

        Map<String, Object> jsonData = commonFUNC.json2Map(jsonParam);

        List<Map<String, Object>> result_list = settingService.get_SetRecords(jsonData);
        System.out.println(result_list);
        return result_list;
    }

    @GetMapping("/OrdersPage/Detail")
    @ResponseBody
    public List<Map<String, Object>> getDetail(
            @RequestParam(value = "json", required = false) String jsonParam)
            throws IOException {

        Map<String, Object> jsonData = commonFUNC.json2Map(jsonParam);

        List<Map<String, Object>> result_list = settingService.get_Detail(jsonData);
        System.out.println(result_list);
        return result_list;
    }
}
