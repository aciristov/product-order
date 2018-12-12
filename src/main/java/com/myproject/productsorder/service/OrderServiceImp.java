package com.myproject.productsorder.service;

import com.myproject.productsorder.domain.Order;
import com.myproject.productsorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    public OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> listAll() {
        return orderRepository.findAll();

    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).get();
    }
}
