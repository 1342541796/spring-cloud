package com.fanwei.service.impl;

import com.fanwei.config.RestConfig;
import com.fanwei.order.bean.Order;
import com.fanwei.product.bean.Product;
import com.fanwei.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;
    @Override
    public Order createOrder(Long userid, Long productId) {
        Order Order = new Order();
        Order.setUserid(userid);
        Order.setNickName("fanwei");
        Order.setAddress("beijing");
//        Product product = getRemoteProductByid(productId);
//        Product product = getRemoteProductByidWithLoadBalance(productId);
        //基于注解式负载均衡
        Product product = getRemoteProduct(productId);
        // 使用 BigDecimal.multiply 进行精确计算
        BigDecimal price = product.getPrice();
        BigDecimal num = BigDecimal.valueOf(product.getNum());
        Order.setTotalAmount(price.multiply( num));
        Order.setProductList(Arrays.asList( product));
        return Order;
    }

    public Product getRemoteProductByid(Long id) {
        //获取到商品服务所在的所有机器IP+port
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        String url ="http://" + instances.get(0).getHost() + ":" + instances.get(0).getPort();

        Product product = restTemplate.getForObject(  url+"/product/getProduct/" + id, Product.class);
        log.info("调用商品服务成功，返回数据：{}", product);
        return product;
    }

    /**
     * 基于注解式负载均衡
     * @param id
     * @return
     */
    public Product getRemoteProduct(Long id) {
        //获取到商品服务所在的所有机器IP+port
        String url ="http://service-product/product/getProduct/" + id;

        Product product = restTemplate.getForObject(  url, Product.class);
        log.info("调用商品服务成功，返回数据：{}", product);
        return product;
    }

    /**
     * 负载均衡地调用
     * @param id
     * @return
     */
    public Product getRemoteProductByidWithLoadBalance(Long id) {
        //获取到商品服务所在的所有机器IP+port
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-product");
        String url ="http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort()+"/product/getProduct/" + id;

        Product product = restTemplate.getForObject(url, Product.class);
        log.info("调用商品服务成功，返回数据：{}", product);
        return product;
    }
}
