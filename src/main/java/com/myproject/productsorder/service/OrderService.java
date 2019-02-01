package com.myproject.productsorder.service;

import com.myproject.productsorder.domain.Order;
import com.myproject.productsorder.domain.OrderPojo;
import com.myproject.productsorder.domain.OrderProductPojo;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order saveOrder(OrderPojo orderPojo, Long userId);
    List<OrderProductPojo> getProducts(Long orderId);

    void update(Long id, Order order);
    void delete(Order o);
    
}
