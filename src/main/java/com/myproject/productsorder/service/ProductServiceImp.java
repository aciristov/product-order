package com.myproject.productsorder.service;

import com.myproject.productsorder.domain.Product;
import com.myproject.productsorder.repository.ProductRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Session;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product){
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public void update(Long id, Product product){
        productRepository.save(product);
    }

    @Override
    public void delete(Product p){
        productRepository.delete(p);
    }



}
