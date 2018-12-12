package com.myproject.productsorder.service;

import com.myproject.productsorder.domain.Order;
import com.myproject.productsorder.domain.Product;

import java.util.List;

public interface OrderService {

    Order save(Order order);
    List<Order> listAll();
    Order getOrder(Long id);


}
