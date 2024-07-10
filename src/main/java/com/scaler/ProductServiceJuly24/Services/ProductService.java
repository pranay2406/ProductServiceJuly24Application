package com.scaler.ProductServiceJuly24.Services;

import com.scaler.ProductServiceJuly24.Models.Product;

import java.util.List;

public interface ProductService {
      Product getSingleProduct(Long productId);
      List<Product> getAllProducts();

}
