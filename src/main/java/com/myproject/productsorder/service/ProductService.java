package com.myproject.productsorder.service;


import com.myproject.productsorder.domain.Product;

import java.util.List;

public interface ProductService  {

    Product save(Product product);
    void update(Long id, Product product);
    void delete(Product p);

}
