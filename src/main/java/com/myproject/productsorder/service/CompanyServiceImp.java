package com.myproject.productsorder.service;

import com.myproject.productsorder.domain.Company;
import com.myproject.productsorder.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImp implements CompanyService {

    @Autowired
    public CompanyRepository companyRepository;

    @Override
    public Company save(Company company){
        return companyRepository.save(company);
    }

    @Override
    public List<Company> listAll(){
        return companyRepository.findAll();
    }

    //MUST USE .GET(), BECAUSE ID IS DEFINED AS "Optional", IN CASE ID IS MISSING
    @Override
    public Company getCompany(Long id) {
        return companyRepository.findById(id).get();

    }

}
