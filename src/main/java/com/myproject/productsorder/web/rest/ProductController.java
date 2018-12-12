package com.myproject.productsorder.web.rest;

import com.myproject.productsorder.domain.Product;
import com.myproject.productsorder.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/productAPI")
public class ProductController {
    private static final Logger logger = Logger.getLogger(ProductController.class.toString());

    @Autowired
    private ProductService productService;

    //CREATE NEW PRODUCT
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        Product newProduct = productService.save(product);
        logger.info("New product has been saved with ID: " + newProduct.getId());
        return ResponseEntity.ok().body(newProduct);
    }

    //GET ALL PRODUCTS
    @RequestMapping(value = "/getproducts", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> list() {
        List<Product> products = productService.listAll();
        if (products.isEmpty()){
            logger.info("There are no products");
        }
        else{
            logger.info("All products has been listed");
        }
        return ResponseEntity.ok().body(products);
    }

    // GET PRODUCT BY ID
    @RequestMapping(value = "/getproduct/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProduct(id);

        return ResponseEntity.ok().body(product);
    }

//    // UPDATE PRODUCT WITH ID
//    @RequestMapping(value = "/updateproduct/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<?> updateProduct(@RequestBody Product product){
//
//    }


}
