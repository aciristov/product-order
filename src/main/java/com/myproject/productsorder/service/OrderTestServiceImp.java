package com.myproject.productsorder.service;

import com.myproject.productsorder.domain.OrderTest;
import com.myproject.productsorder.repository.OrderTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderTestServiceImp implements OrderTestService {

    @Autowired
    private OrderTestRepository orderTestRepository;

    @Override
    public OrderTest save(OrderTest orderTest){
        return orderTestRepository.save(orderTest);
    }

    @Override
    public List<OrderTest> listAll(){
        return orderTestRepository.findAll();
    }

    @Override
    public OrderTest getOrderTest(Long id){
        return orderTestRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void update(Long id, OrderTest orderTest){
        orderTestRepository.save(orderTest);
    }

    @Override
    public void delete(OrderTest orderTest){
        orderTestRepository.delete(orderTest);
    }

}
