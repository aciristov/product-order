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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     *
     */



}
