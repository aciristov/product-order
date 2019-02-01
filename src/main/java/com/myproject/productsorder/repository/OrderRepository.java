package com.myproject.productsorder.repository;

import com.myproject.productsorder.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order save(Order order);
    Page<Order> findByUserId(Long userId, Pageable pageable);
    List<Order> findAll();
    Optional<Order> findById(Long id);
}
