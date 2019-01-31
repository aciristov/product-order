package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.*;
import com.myproject.productsorder.exception.ResourceNotFoundException;
import com.myproject.productsorder.repository.OrderTestRepository;
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
import sun.misc.Request;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orderAPI")
@Secured(AuthoritiesConstants.USER)
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderTestRepository orderTestRepository;

    @Autowired
    private UserRepository userRepository;

    /*
    * Just for testing purposes
    * Every user post his own order -> next post method
     */
    @Autowired
    private UserService userService;

//    /*
//    * Post order for authorized user
//     */
//    @PostMapping("/user/orders")
//    public OrderTest createUserOrderTest(Long userId, @Valid @RequestBody OrderTest orderTest){
//        userId = userService.getUserWithAuthorities().get().getId();
//        return userRepository.findById(userId).map(user -> {
//            orderTest.setUser(user);
//            return orderTestRepository.save(orderTest);
//        }).orElseThrow(() -> new ResourceNotFoundException("User does not exist!"));
//    }


    /*
     * Get orders for authorized user
     */
    @GetMapping("/user/orders")
    public Page<OrderTest> getAllOrdersByUserIdTest(Pageable pageable){
        Long userId = userService.getUserWithAuthorities().get().getId();
        return orderTestRepository.findByUserId(userId , pageable);
    }

    @GetMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<OrderTest> getordertestById1(@PathVariable Long orderId){
        OrderTest orderTest = orderTestRepository.findById(orderId).get();
        return ResponseEntity.ok().body(orderTest);
    }

    @PostMapping("/orders")
    public OrderTest createOrder(@Valid @RequestBody OrderPojo orderPojo){
        Long userId = userService.getUserWithAuthorities().get().getId();
        return orderService.saveOrder(orderPojo, userId);
    }


    //test GET PRODUCTS FOR ORDER_ID
    @GetMapping("/orders/{orderId}/products")
    public List<OrderProductPojo> getordertestById(@PathVariable (value = "orderId") Long orderId){
        return orderService.getProducts(orderId);
    }

    @PutMapping("/user/orders/{orderId}")
    public OrderTest updateOrderTest(@PathVariable Long orderId,
                                     @Valid @RequestBody OrderTest orderTestRequest){
        Long userId = userService.getUserWithAuthorities().get().getId();
        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }
        return orderTestRepository.findById(orderId).map(orderTest -> {
            orderTest.setDescription(orderTestRequest.getDescription());
            orderTest.setOrderDate(orderTestRequest.getOrderDate());
            return orderTestRepository.save(orderTest);
        }).orElseThrow(()-> new ResourceNotFoundException("OrderTestId " + orderId + " not found"));
    }

    @DeleteMapping("user/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(Long userId,
                                         @PathVariable Long orderId){
        userId = userService.getUserWithAuthorities().get().getId();
        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }
        return orderTestRepository.findById(orderId).map(orderTest -> {
            orderTestRepository.delete(orderTest);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("OrderId " + orderId + " not found"));

    }

}
