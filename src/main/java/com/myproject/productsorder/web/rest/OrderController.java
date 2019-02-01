package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.*;
import com.myproject.productsorder.exception.ResourceNotFoundException;
import com.myproject.productsorder.repository.OrderRepository;
import com.myproject.productsorder.repository.UserRepository;
import com.myproject.productsorder.security.AuthoritiesConstants;
import com.myproject.productsorder.service.OrderService;
import com.myproject.productsorder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orderAPI")
@Secured(AuthoritiesConstants.USER)
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/user/orders")
    public Page<Order> getAllOrdersByUserIdTest(Pageable pageable){
        Long userId = userService.getUserWithAuthorities().get().getId();
        return orderRepository.findByUserId(userId , pageable);
    }

    @GetMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<Order> getordertestById1(@PathVariable Long orderId){
        Order order = orderRepository.findById(orderId).get();
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders")
    public Order createOrder(@Valid @RequestBody OrderPojo orderPojo){
        Long userId = userService.getUserWithAuthorities().get().getId();
        return orderService.saveOrder(orderPojo, userId);
    }

    //test GET PRODUCTS FOR ORDER_ID -->
    @GetMapping("/orders/{orderId}/products")
    public List<OrderProductPojo> getorderById(@PathVariable (value = "orderId") Long orderId){
        return orderService.getProducts(orderId);
    }

    @PutMapping("/user/orders/{orderId}")
    public Order updateOrder(@PathVariable Long orderId,
                                 @Valid @RequestBody Order orderRequest){
        Long userId = userService.getUserWithAuthorities().get().getId();
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }
        return orderRepository.findById(orderId).map(order -> {
            order.setDescription(orderRequest.getDescription());
            order.setOrderDate(orderRequest.getOrderDate());
            return orderRepository.save(order);
        }).orElseThrow(()-> new ResourceNotFoundException("OrderTestId " + orderId + " not found"));
    }

    @DeleteMapping("user/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(Long userId,
                                         @PathVariable Long orderId){
        userId = userService.getUserWithAuthorities().get().getId();
        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }
        return orderRepository.findById(orderId).map(orderTest -> {
            orderRepository.delete(orderTest);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("OrderId " + orderId + " not found"));

    }

}
