package com.myproject.productsorder.repository;

import com.myproject.productsorder.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order save(Order order);

    List<Order> findAll();
    // optional in case can't find the ID
    Optional<Order> findById(Long id);

}
