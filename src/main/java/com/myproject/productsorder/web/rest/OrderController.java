package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.Order;
import com.myproject.productsorder.service.OrderService;
import com.myproject.productsorder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controllerAPI")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        Order newOrder = orderService.save(order);
        return ResponseEntity.ok().body(newOrder);
    }

    @RequestMapping(value = "/getorders", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> list() {
        List<Order> orders = orderService.listAll();
        return ResponseEntity.ok().body(orders);
    }

    @RequestMapping(value = "/getorder/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
        Order order = orderService.getOrder(id);
        return ResponseEntity.ok().body(order);
    }


}
