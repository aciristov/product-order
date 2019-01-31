package com.myproject.productsorder.service;


import com.myproject.productsorder.domain.*;

import java.util.List;

public interface OrderService {

    Order save(Order order);

    OrderTest saveOrder(OrderPojo orderPojo, Long userId);

    List<Order> listAll();
    OrderTest getOrder(Long id);
    void update(Long id, Order order);
    void delete(Order o);
    List<OrderProductPojo> getProducts(Long orderId);

}
