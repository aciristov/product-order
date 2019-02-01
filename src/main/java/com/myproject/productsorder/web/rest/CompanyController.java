package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.Company;
import com.myproject.productsorder.exception.ResourceNotFoundException;
import com.myproject.productsorder.repository.CompanyRepository;
import com.myproject.productsorder.security.AuthoritiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/companyAPI")
@Secured(AuthoritiesConstants.USER)
public class CompanyController {

    @Autowired
    public CompanyRepository companyRepository;

    @PostMapping("/companies")
    public Company createCompany(@Valid @RequestBody Company company) {
        Company newCompany = companyRepository.save(company);
        return newCompany;
    }

    @GetMapping("/companies")
    public Page<Company> getAllCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @GetMapping(value = "/companies/{id}")
    public ResponseEntity<Company> getcompanyById(@PathVariable Long id) {
        Company company = companyRepository.findById(id).get();
        return ResponseEntity.ok().body(company);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Object> updateCompany(@RequestBody Company company, @PathVariable long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (!companyOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        company.setId(id);
        companyRepository.save(company);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable(name = "id") Long companyId) {
        return companyRepository.findById(companyId).map(company -> {
            companyRepository.delete(company);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CompanyId " + companyId + " not found"));
    }

}
