package com.myproject.productsorder.service;

import com.myproject.productsorder.domain.OrderTest;

import java.util.List;

public interface OrderTestService {
    OrderTest save(OrderTest orderTest);
    List<OrderTest> listAll();
    OrderTest getOrderTest(Long id);
    void update(Long id, OrderTest orderTest);
    void delete(OrderTest o);
}
