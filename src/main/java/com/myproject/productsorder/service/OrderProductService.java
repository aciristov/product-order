package com.myproject.productsorder.service;

import com.myproject.productsorder.domain.OrderProduct;

import java.util.List;

public interface OrderProductService {
    OrderProduct save(OrderProduct orderProduct);
    List<OrderProduct> listAll();
    OrderProduct getProduct(Long id);
    void update(Long id, OrderProduct orderProduct);
    void delete(OrderProduct o);
}
