package com.myproject.productsorder.service;

import com.myproject.productsorder.domain.*;
import com.myproject.productsorder.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public OrderTestRepository orderTestRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public OrderProductRepository orderProductRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> listAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderTest getOrder(Long id) {
        return orderTestRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void update(Long id, Order order){
        orderRepository.save(order);
    }

    @Override
    public void delete(Order o){
        orderRepository.delete(o);
    }

    @Override
    public List<OrderProductPojo> getProducts(Long orderId) {
        List<OrderProductPojo> orderProductPojos = new ArrayList<>();
        List<OrderProduct> orderProducts = orderProductRepository.findByOrderId(orderId);
        for(OrderProduct orderProduct: orderProducts) {
            Optional<Product> productOptional = productRepository.findById(orderProduct.getProductId());
            productOptional.ifPresent(product -> {
                OrderProductPojo orderProductPojo = new OrderProductPojo(
                    product.getId(),
                    product.getDescription(),
                    product.getName(),
                    orderProduct.getQuantity(),
                    orderProduct.getUnit_price()
                );
                orderProductPojos.add(orderProductPojo);
            });
        }
        return orderProductPojos;
    }

    @Override
    public OrderTest saveOrder(OrderPojo orderPojo, Long userId) {
        User user = userRepository.findById(userId).get();
        OrderTest order = new OrderTest();
        order.setDescription(orderPojo.getDescription());
        order.setOrderDate(new Date());
        order.setUser(user);
        order = orderTestRepository.save(order);

        for (OrderProdPojo p : orderPojo.getProducts()){
            OrderProduct o = new OrderProduct();
            o.setOrderId(order.getId());
            o.setProductId(p.getProductId());
            o.setQuantity(p.getQuantity());
            o.setUnit_price(p.getPrice());
            orderProductRepository.save(o);
        }
        return order;
    }
}
