package com.scaler.ProductServiceJuly24.Controllers;


import com.scaler.ProductServiceJuly24.DTO.FakeStoreProductDTO;
import com.scaler.ProductServiceJuly24.Models.Product;
import com.scaler.ProductServiceJuly24.Services.FakeStoreProductServices;
import com.scaler.ProductServiceJuly24.Services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
            private ProductService productService;

            public ProductController(ProductService productService) {
                this.productService = productService;
            }


            // http://localhost/products/2
            @GetMapping("/{id}")
            public Product getProductById(@PathVariable("id") long id){
                return productService.getSingleProduct(id);
            }

            public List<Product> getAllProducts(){
                return new ArrayList<>();
            }

           /* public addNewProduct()
            {

            }*/
}
