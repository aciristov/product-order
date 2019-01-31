package com.myproject.productsorder.repository;

import com.myproject.productsorder.domain.OrderProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    Page<OrderProduct> findByProductId(Long productId, Pageable pageable);
    List <OrderProduct> findByOrderId(Long orderId);
    Optional<OrderProduct> findById(Long id);

}
