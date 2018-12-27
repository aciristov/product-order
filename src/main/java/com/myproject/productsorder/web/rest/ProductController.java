package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.Company;
import com.myproject.productsorder.domain.Product;
import com.myproject.productsorder.exception.ResourceNotFoundException;
import com.myproject.productsorder.repository.CompanyRepository;
import com.myproject.productsorder.repository.ProductRepository;
import com.myproject.productsorder.security.AuthoritiesConstants;
import com.myproject.productsorder.service.ProductService;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/productAPI")
@Secured(AuthoritiesConstants.USER)
public class ProductController {
    private static final Logger logger = Logger.getLogger(ProductController.class.toString());

//    @Autowired
//    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;



    //SAVE NEW PRODUCT TO COMPANY_ID
    @PostMapping("/companies/{companyId}/products")
    public Product createProduct(@PathVariable (value = "companyId") Long companyId, @Valid @RequestBody Product product ){
        return companyRepository.findById(companyId).map(company -> {
            product.setCompany(company);
//            product.set
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("CompanyId " + companyId + " not found"));
    }

//    //CREATE NEW PRODUCT
//    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
//        Product newProduct = productService.save(product);
//        logger.info("New product has been saved with ID: " + newProduct.getId());
//        return ResponseEntity.ok().body(newProduct);
//    }


    //GET PRODUCT OF COMPANY_ID
    @GetMapping("/companies/{companyId}/products")
    public Page<Product> getAllProductsByCompanyId(@PathVariable (value = "companyId") Long companyId, Pageable pageable){
        return productRepository.findByCompanyId(companyId, pageable);
    }

//    // GET PRODUCT BY ID
//    @RequestMapping(value = "/getproduct/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
//        Product product = productService.getProduct(id);
//
//        return ResponseEntity.ok().body(product);
//    }



    // EDIT PRODUCT FOR COMPANY_ID
    @PutMapping("/companies/{companyId}/products/{productId}")
    public Product updateProduct(@PathVariable (value = "companyId") Long companyId,
                                 @PathVariable (value = "productId") Long productId,
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


//    // UPDATE PRODUCT WITH ID
//    @RequestMapping(value = "/updateproduct/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> update(@PathVariable("/updateproduct/id") Long id, @RequestBody Product product){
//        Product tempProduct = productService.getProduct(product.getId());
//        if (tempProduct == null){
//            logger.info("Product for UPDATE NOT found with id=" + id);
//        }
//        product.setId(id);
//        productService.update(id, product);
//        logger.info("Product has been UPDATED successfully with id: " + id);
//        return ResponseEntity.ok().body(product);
//
//    }


    // DELETE PRODUCT FOR COMPANY_ID

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

//    // DELETE PRODUCT
//    @RequestMapping(value = "/deleteproduct/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> delete(@PathVariable("id") Long id){
//        Product product = productService.getProduct(id);
//        if (product == null){
//            logger.info("Product for DELETE NOT found with id=" + id);
//        }
//        productService.delete(id);
//        logger.info("Book has been DELETED successfully ");
//        return ResponseEntity.ok().body(product);
//
//    }



}
