package com.myproject.productsorder.service;

import com.myproject.productsorder.domain.*;
import com.myproject.productsorder.repository.OrderProductRepository;
import com.myproject.productsorder.repository.OrderRepository;
import com.myproject.productsorder.repository.ProductRepository;
import com.myproject.productsorder.repository.UserRepository;
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
    private OrderRepository orderRepository;

    @Autowired
    public OrderProductRepository orderProductRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public UserRepository userRepository;

    @Override
    public Order save(Order order){
        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public void update(Long id, Order order){
        orderRepository.save(order);
    }

    @Override
    public void delete(Order order){
        orderRepository.delete(order);
    }

    @Override
    public Order saveOrder(OrderPojo orderPojo, Long userId) {

        User user = userRepository.findById(userId).get();
        Order order = new Order();
        order.setDescription(orderPojo.getDescription());
        order.setOrderDate(new Date());
        order.setUser(user);
        order = orderRepository.save(order);

        for (OrderProdPojo p : orderPojo.getProducts()){
            OrderProduct o = new OrderProduct();
            o.setOrderId(order.getId());
            o.setProductId(p.getProductId());
            o.setQuantity(p.getQuantity());
            o.setUnit_price(p.getPrice());//
//              o.setQuantity(Double.parseDouble(getQuantityByProductId(String.valueOf(o.getProductId()))));
//            o.setUnit_price(Double.parseDouble(getPriceByProductId(String.valueOf(o.getProductId()))));

            orderProductRepository.save(o);
        }
        return order;
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


}
