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

    // Dependency Injection of the ProductRepository, implements Product Service
    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    private SessionFactory sessionFactory;

    @Override
    public Product save(Product product){
        return productRepository.save(product);
    }

    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
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
