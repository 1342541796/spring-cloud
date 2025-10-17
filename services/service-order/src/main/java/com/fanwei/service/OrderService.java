package com.fanwei.service;

import com.fanwei.order.bean.Order;

public interface OrderService {
    Order createOrder(Long userid, Long productId);
}
