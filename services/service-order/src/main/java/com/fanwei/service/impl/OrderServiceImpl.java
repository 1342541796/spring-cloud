package com.fanwei.service.impl;

import com.fanwei.config.RestConfig;
import com.fanwei.order.bean.Order;
import com.fanwei.product.bean.Product;
import com.fanwei.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestConfig restConfig;
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

    public Product getRemoteProductByid(Long id) {

        restConfig.restTemplate().getForObject("http://service-product/product/getProduct/" + id, Product.class);
        Product product = new Product();
        product.setId(id);
        product.setProductName("华为手机");
        product.setPrice(new BigDecimal(1000));
        product.setNum(100l);
        return product;
    }
}
