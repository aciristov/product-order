package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.Product;
import com.myproject.productsorder.exception.ResourceNotFoundException;
import com.myproject.productsorder.repository.CompanyRepository;
import com.myproject.productsorder.repository.ProductRepository;
import com.myproject.productsorder.security.AuthoritiesConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/productAPI")
@Secured(AuthoritiesConstants.USER)
public class ProductController {

//    @Autowired
//    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;


    @PostMapping("/companies/{companyId}/products")
    public Product createProduct(@PathVariable Long companyId, @Valid @RequestBody Product product ){
        return companyRepository.findById(companyId).map(company -> {
            product.setCompany(company);
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("CompanyId " + companyId + " not found"));
    }


    @GetMapping("/companies/{companyId}/products")
    public Page<Product> getAllProductsByCompanyId(@PathVariable (value = "companyId") Long companyId, Pageable pageable){
        return productRepository.findByCompanyId(companyId, pageable);
    }

    @GetMapping("/companies/{companyId}/products/{productId}")
    public ResponseEntity<Product> getproductById(@PathVariable Long productId) {
        Product product = productRepository.findById(productId).get();
        return ResponseEntity.ok().body(product);
    }


    @PutMapping("/companies/{companyId}/products/{productId}")
    public Product updateProduct(@PathVariable Long companyId,
                                 @PathVariable Long productId,
                                 @Valid @RequestBody Product productRequest){
        if (!companyRepository.existsById(companyId)){
            throw new ResourceNotFoundException("companyId " + companyId + " not found");
        }
        return productRepository.findById(productId).map(product -> {
            product.setName(productRequest.getName());
            product.setUnitprice(productRequest.getUnitprice());
            product.setDescription(productRequest.getDescription());
            product.setAvailable(productRequest.isAvailable());
            return productRepository.save(product);
        }).orElseThrow(()-> new ResourceNotFoundException("ProductId " + productId + " not found"));
    }


    @DeleteMapping("/companies/{companyId}/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long companyId,
                                           @PathVariable Long productId) {
            if(!companyRepository.existsById(companyId)){
                throw new ResourceNotFoundException("CompanyId " + companyId + " not found");
            }

        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("ProductId " + productId + " not found"));

    }


}
