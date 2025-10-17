package com.fanwei.order.bean;

import com.fanwei.product.bean.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private Long id;
    private BigDecimal totalAmount;
    private Long userid;
    private String nickName;
    private String address;
    private List<Product> productList;
}
