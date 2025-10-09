package com.fanwei.service;

import com.fanwei.bean.Order;

public interface OrderService {
    Order createOrder(Long userid, Long productId);
}
