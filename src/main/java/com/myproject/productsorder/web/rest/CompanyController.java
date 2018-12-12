package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.Company;
import com.myproject.productsorder.service.CompanyService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companyAPI")
public class CompanyController {

    @Autowired
    public CompanyService companyService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCompany(@RequestBody Company company) {
        Company newCompany = companyService.save(company);
        return ResponseEntity.ok().body(newCompany);
    }

    @RequestMapping(value = "/getcompanies", method = RequestMethod.GET)
    public ResponseEntity<List<Company>> list() {
        List<Company> companies = companyService.listAll();
        return ResponseEntity.ok().body(companies);
    }


    @RequestMapping(value = "/getcompany/{id}", method = RequestMethod.GET)
    public ResponseEntity<Company> getcompanyById(@PathVariable("id") Long id) {
        Company company = companyService.getCompany(id);
        return ResponseEntity.ok().body(company);
    }



}
