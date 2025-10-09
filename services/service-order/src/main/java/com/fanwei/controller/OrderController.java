package com.fanwei.controller;

import com.fanwei.bean.Order;
import com.fanwei.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    @GetMapping("/create")
    public Order createOrder(@RequestParam("userid") Long userid,
                             @RequestParam("productId") Long productId){

        Order order = orderService.createOrder(userid, productId);
        return  order;
    }
}
