package com.myproject.productsorder.repository;

import com.myproject.productsorder.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// in ProductRepository, using JpaRepository and define crud methods(save, list all, findById)
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCompanyId(Long companyId, Pageable pageable);

    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long id);

}
