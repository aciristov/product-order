package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.Company;
import com.myproject.productsorder.exception.ResourceNotFoundException;
import com.myproject.productsorder.repository.CompanyRepository;
import com.myproject.productsorder.security.AuthoritiesConstants;
import com.myproject.productsorder.service.CompanyService;
import com.netflix.discovery.converters.Auto;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/companyAPI")
@Secured(AuthoritiesConstants.USER)
public class CompanyController {

//    @Autowired
//    public CompanyService companyService;

    @Autowired
    public CompanyRepository companyRepository;

    // SAVE NEW COMPANY
    @PostMapping("/companies")
    public Company createCompany(@Valid @RequestBody Company company){
        Company newCompany = companyRepository.save(company);
        return newCompany;
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> saveCompany(@RequestBody Company company) {
//        Company newCompany = companyService.save(company);
//        return ResponseEntity.ok().body(newCompany);
//    }

    // GET ALL COMPANIES
    @GetMapping("/companies")
    public Page<Company> getAllCompanies(Pageable pageable){
        return companyRepository.findAll(pageable);
    }

//    @RequestMapping(value = "/getcompanies", method = RequestMethod.GET)
//    public ResponseEntity<List<Company>> list() {
//        List<Company> companies = companyService.listAll();
//        return ResponseEntity.ok().body(companies);
//    }

    //EDIT COMPANY BY ID
    @PutMapping("/companies/{id}")
    public Company updateCompany(@Valid @RequestBody Company companyRequest, @PathVariable(name = "id") Long companyId){
        if (!companyRepository.existsById(companyId)){
            throw new ResourceNotFoundException("CompanyId " + companyId + " not exist");
        }

        return companyRepository.findById(companyId).map(company -> {
            company.setName(companyRequest.getName());
            company.setCity(companyRequest.getCity());
            company.setPhone(companyRequest.getPhone());
            company.setAddress(companyRequest.getAddress());
            return companyRepository.save(company);
        })
            .orElseGet(() ->{
                companyRequest.setId(companyId);
                return companyRepository.save(companyRequest);
            });

//        return companyRepository.findById(companyId).map(company -> {
//            company.setName(companyRequest.getName());
//            company.setCity(companyRequest.getCity());
//            company.setPhone(companyRequest.getPhone());
//            company.setAddress(companyRequest.getAddress());
//            return companyRepository.save(companyRequest);
//        }).orElseThrow(() -> new ResourceNotFoundException("CompanyId " + companyId + " not founc"));
    }

    // GET COMPANY BY ID
    @GetMapping(value = "/companies/{id}")
    public ResponseEntity<Company> getcompanyById(@PathVariable Long id) {
        Company company = companyRepository.findById(id).get();
        return ResponseEntity.ok().body(company);
    }

    //DELETE COMPANY BY ID
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable(name = "id") Long companyId){
        return companyRepository.findById(companyId).map(company -> {
            companyRepository.delete(company);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CompanyId " + companyId + " not found"));
    }



}
