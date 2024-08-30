package com.scaler.ProductServiceJuly24.Controllers;


import com.scaler.ProductServiceJuly24.DTO.FakeStoreProductDTO;
import com.scaler.ProductServiceJuly24.Models.Product;
import com.scaler.ProductServiceJuly24.Services.FakeStoreProductServices;
import com.scaler.ProductServiceJuly24.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

            private ProductService productService;

            public ProductController(ProductService productService) {
                this.productService = productService;
            }


            //http://localhost:8080/products
            @GetMapping("/{id}")
            public ResponseEntity<Product> getProductById(@PathVariable("id") long id){
                ResponseEntity<Product> response = new ResponseEntity<>(
                        productService.getSingleProduct(id),
                        HttpStatus.OK
                );
                return response;
            }

            //http://localhost:8080/products
            @GetMapping()
            public List<Product> getAllProducts(){
                return productService.getAllProducts();
            }

            //'http://localhost:8080/products?limit=5'
            @GetMapping("/limit")
            public List<Product> getProductByLimit(@RequestParam int limit){
                return productService.getProductByLimit(limit);
            }

            @GetMapping("/sort")
            public List<Product> getAllProductInDesc(@RequestParam String sort)
            {
                return productService.getAllProductInDesc(sort);
            }

            @GetMapping("/categories")
            public List<String> getAllProductCategory(){
                return productService.getAllProductCategory();
            }

            @GetMapping("/category/{categoryName}")
            public List<Product> getProductByCategory(@PathVariable("categoryName") String categoryName){
                return productService.getProductByCategory(categoryName);
            }

            @PatchMapping("/{id}")
             public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
                 return productService.updateProduct(id, product);
            }

            @PutMapping("/{id}")
            public Product replaceProduct(@PathVariable("id") long id, @RequestBody Product product){
                return productService.replaceProduct(id, product);
            }
}
