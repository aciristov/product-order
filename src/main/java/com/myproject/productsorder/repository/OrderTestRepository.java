package com.myproject.productsorder.repository;

import com.myproject.productsorder.domain.OrderTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderTestRepository extends JpaRepository<OrderTest, Long> {
    Page<OrderTest> findByUserId(Long userId, Pageable pageable);
    Optional<OrderTest> findById(Long id);
}
