package com.fanwei.service.impl;

import com.fanwei.bean.Order;
import com.fanwei.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(Long userid, Long productId) {
        Order Order = new Order();
        Order.setUserid(userid);
        Order.setNickName("fanwei");
        Order.setAddress("beijing");
        Order.setTotalAmount(new BigDecimal(100));
        Order.setProductList(null);
        return Order;
    }
}
