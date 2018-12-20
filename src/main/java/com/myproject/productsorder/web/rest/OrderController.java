package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.Order;
import com.myproject.productsorder.exception.ResourceNotFoundException;
import com.myproject.productsorder.repository.OrderRepository;
import com.myproject.productsorder.repository.UserRepository;
import com.myproject.productsorder.service.OrderService;
import com.myproject.productsorder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/controllerAPI")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    // SAVE NEW ORDER
    @PostMapping("/orders")
    public Order createOrder(@Valid @RequestBody Order order){
        return orderRepository.save(order);
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
//        Order newOrder = orderService.save(order);
//        return ResponseEntity.ok().body(newOrder);
//    }

    // GET ALL ORDERS
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> list() {
        List<Order> orders = orderService.listAll();
        return ResponseEntity.ok().body(orders);
    }

    // GET ORDER BY ID
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
        Order order = orderService.getOrder(id);
        return ResponseEntity.ok().body(order);
    }


//    // EDIT ORDER BY ID
//    @PutMapping("/orders/{id}")
//    public Order updateOrder(@PathVariable Long orderId, @Valid @RequestBody Order orderRequest){
//        return orderRepository.findById(orderId).map(order -> {
//            order.setQuantity(orderRequest.getQuantity());
//            order.setDescription(orderRequest.getDescription());
//            order.setOrderDate(orderRequest.getOrderDate());
//            return orderRepository.save(order);
//        }).orElseThrow(() -> new ResourceNotFoundException("OrderId " + orderId + " not found"));
//    }

    // EDIT ORDER FOR USER
    @PutMapping("/users/{userId}/orders/{orderId}")
    public Order updateOrder(@PathVariable (value = "userId") Long userId,
                             @PathVariable (value = "orderId") Long orderId,
                             @Valid @RequestBody Order orderRequest){
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }
//        return orderRepository.findById(orderId).map(order -> {
//            order.setDescription(orderRequest.getDescription());
//            order.setQuantity(orderRequest.getQuantity());
//            order.setOrderDate(orderRequest.getOrderDate());
            return orderRepository.save(orderRequest);
//        }).orElseThrow(()-> new ResourceNotFoundException("OrderId " + orderId + " not found"));

    }

    //DETELE ORDER BY USER
    @DeleteMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable (value="userId")Long userId,
                                         @PathVariable (value="orderId") Long orderId){
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }

        return orderRepository.findById(orderId).map(order -> {
            orderRepository.delete(order);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("OrderId " + orderId + " not found"));

    }


//    //DELETE ORDER BY ID
//    @DeleteMapping("/orders/{id}")
//    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId){
//        return orderRepository.findById(orderId).map(order -> {
//            orderRepository.delete(order);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("OrderId " + orderId + " not found"));
//    }

}
