package com.myproject.productsorder.service;

import com.myproject.productsorder.domain.OrderProduct;
import com.myproject.productsorder.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderProductServiceImp implements OrderProductService{

    // Dependency Injection of the OrderProductRepository, implements OrderProduct Service
    @Autowired
    private OrderProductRepository orderProductRepository;

    @Override
    public OrderProduct save(OrderProduct orderProduct){
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public List<OrderProduct> listAll(){
        return orderProductRepository.findAll();
    }

    @Override
    public OrderProduct getProduct(Long id) {
        return orderProductRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void update(Long id, OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }

    @Override
    public void delete(OrderProduct op){
        orderProductRepository.delete(op);
    }

}
