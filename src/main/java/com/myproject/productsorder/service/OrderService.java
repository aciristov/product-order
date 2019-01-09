package com.myproject.productsorder.service;


import com.myproject.productsorder.domain.Order;

import java.util.List;

public interface OrderService {

    Order save(Order order);
    List<Order> listAll();
    Order getOrder(Long id);
    void update(Long id, Order order);
    void delete(Order o);

}
