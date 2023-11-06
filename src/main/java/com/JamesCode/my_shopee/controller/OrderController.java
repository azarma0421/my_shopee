package com.JamesCode.my_shopee.controller;

import com.JamesCode.my_shopee.service.CommonFUNC;
import com.JamesCode.my_shopee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private OrderService orderService;

    private CommonFUNC commonFUNC;

    @Autowired
    public OrderController(OrderService theOrderService, CommonFUNC thecommonFUNC){
        orderService = theOrderService;
        commonFUNC = thecommonFUNC;
    }

    @PostMapping("/OrdersPage")
    public String showPage(){
        return "Orders";
    }
}
