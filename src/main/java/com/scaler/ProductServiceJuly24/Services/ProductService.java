package com.scaler.ProductServiceJuly24.Services;

import com.scaler.ProductServiceJuly24.Models.Product;

import java.util.List;

public interface ProductService {
      Product getSingleProduct(Long productId);
      List<Product> getAllProducts();
      List<Product> getProductByLimit(int limit);
      List<Product> getAllProductInDesc(String desc);
      List<String> getAllProductCategory();
      List<Product> getProductByCategory(String category);
      Product updateProduct(Long productId, Product product);
      Product replaceProduct(Long productId, Product product);

}
