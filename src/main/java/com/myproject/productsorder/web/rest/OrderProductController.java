package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.OrderProduct;
import com.myproject.productsorder.repository.OrderProductRepository;
import com.myproject.productsorder.repository.OrderRepository;
import com.myproject.productsorder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderProductAPI")
public class OrderProductController {
//
//    @Autowired
//    private OrderProductRepository orderProductRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @GetMapping("/{order_productId}/order_products")
//    public Page<OrderProduct> getAllOrderProducts(@PathVariable(value = "order_productId") Long )



}
