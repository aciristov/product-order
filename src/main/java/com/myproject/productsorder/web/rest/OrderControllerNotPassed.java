package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.Order;
import com.myproject.productsorder.exception.ResourceNotFoundException;
import com.myproject.productsorder.repository.OrderRepository;
import com.myproject.productsorder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/orderNotPassed")
public class OrderControllerNotPassed {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/users/{userId}/orders")
    public Order createOrder(@PathVariable Long userId, @Valid @RequestBody Order order){
        return userRepository.findById(userId).map(user -> {
            order.setUser(user);
            return orderRepository.save(order);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId" + userId + "not found"));
    }

    @PostMapping("/orders")
    public Order createOrder(@Valid @RequestBody Order order) {
        Order newOrder = orderRepository.save(order);
        return newOrder;
    }

    @GetMapping("/orders")
    public Page<Order> getAllOrders(Pageable pageable){
        return orderRepository.findAll(pageable);
    }

    @GetMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<Order> getorderById(@PathVariable Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        return ResponseEntity.ok().body(order);
    }

}
