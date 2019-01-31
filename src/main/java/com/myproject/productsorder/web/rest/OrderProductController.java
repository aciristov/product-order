package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.OrderProduct;
import com.myproject.productsorder.domain.OrderTest;
import com.myproject.productsorder.domain.Product;
import com.myproject.productsorder.repository.OrderProductRepository;
import com.myproject.productsorder.repository.OrderTestRepository;
import com.myproject.productsorder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orderProductAPI")
public class OrderProductController {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderTestRepository orderTestRepository;


    /**
     * return all orders from orderproduct
     */
    @GetMapping("/orders")
    public Page<OrderTest> getAllOrders(Pageable pageable){
        return orderTestRepository.findAll(pageable);
    }

    /**
     * returns all products from orderproduct
     */
    @GetMapping("/products")
    public Page<Product> getAllProducts(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    /**
     * For post:
     * ex: /orderProductAPI/orders/{orderId}/products
     * orderId is order.id from key in page component
     * => all info. that exists from orders and products it is going to be
     * posted again in OrderProduct table
     */

//    @PostMapping("/orders/{orderId}/products")
//    public OrderProduct createOrderProduct(@PathVariable Long orderId,
//                                           @Valid @RequestBody Product product){
//        return orderTestRepository.findById(orderId).map( order -> {
//            product.setOrders(order);
//
//        })
//    }

//    @GetMapping("/orders/{orderId}/products")
//    public Page<OrderProduct>



}
