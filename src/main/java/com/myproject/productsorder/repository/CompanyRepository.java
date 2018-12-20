package com.myproject.productsorder.repository;

import com.myproject.productsorder.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
//    Company save(Company company);
//    List<Company> findAll();
//    Optional<Company> findById(Long id);
}
