package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.OrderTest;
import com.myproject.productsorder.exception.ResourceNotFoundException;
import com.myproject.productsorder.repository.OrderTestRepository;
import com.myproject.productsorder.repository.UserRepository;
import com.myproject.productsorder.security.AuthoritiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orderAPI")
@Secured(AuthoritiesConstants.USER)
public class OrderTestController {

    @Autowired
    private OrderTestRepository orderTestRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/orders")
    public OrderTest createOrder(@RequestBody OrderTest orderTest){
        OrderTest newOrder = orderTestRepository.save(orderTest);
        return newOrder;
    }

    @PostMapping("/users/{userId}/orders")
    public OrderTest createUserOrder(@PathVariable Long userId, @Valid @RequestBody OrderTest orderTest){
        return userRepository.findById(userId).map(user -> {
            orderTest.setUser(user);
            return orderTestRepository.save(orderTest);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found!"));
    }

    @GetMapping("/users/{userId}/orders")
    public Page<OrderTest> getAllOrdersByUserId(@PathVariable Long userId, Pageable pageable){
        return orderTestRepository.findByUserId(userId, pageable);
    }

    @GetMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<OrderTest> getordertestById(@PathVariable Long orderId){
        OrderTest orderTest = orderTestRepository.findById(orderId).get();
        return ResponseEntity.ok().body(orderTest);
    }

    @PutMapping("/users/{userId}/orders/{orderId}")
    public OrderTest updateOrderTest(@PathVariable Long userId,
                                     @PathVariable Long orderId,
                                     @Valid @RequestBody OrderTest orderTestRequest){
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }
        return orderTestRepository.findById(orderId).map(orderTest -> {
            orderTest.setDescription(orderTestRequest.getDescription());
            orderTest.setQuantity(orderTestRequest.getQuantity());
            orderTest.setOrderDate(orderTestRequest.getOrderDate());
            return orderTestRepository.save(orderTest);
        }).orElseThrow(()-> new ResourceNotFoundException("OrderTestId " + orderId + " not found"));
    }

    @DeleteMapping("users/{userId}/orders/{orderId}")
    public ResponseEntity<?> deleteOrderTest(@PathVariable Long userId,
                                             @PathVariable Long orderId){
        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }
        return orderTestRepository.findById(orderId).map(orderTest -> {
            orderTestRepository.delete(orderTest);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("OrderId " + orderId + " not found"));

    }

}
