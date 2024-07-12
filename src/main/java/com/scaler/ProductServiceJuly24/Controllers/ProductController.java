package com.scaler.ProductServiceJuly24.Controllers;


import com.scaler.ProductServiceJuly24.DTO.FakeStoreProductDTO;
import com.scaler.ProductServiceJuly24.Models.Product;
import com.scaler.ProductServiceJuly24.Services.FakeStoreProductServices;
import com.scaler.ProductServiceJuly24.Services.ProductService;
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
            public Product getProductById(@PathVariable("id") long id){
                return productService.getSingleProduct(id);
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


           /* public addNewProduct()
            {

            }*/
}
