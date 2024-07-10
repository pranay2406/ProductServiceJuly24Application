package com.scaler.ProductServiceJuly24.Services;

import com.scaler.ProductServiceJuly24.DTO.FakeStoreProductDTO;
import com.scaler.ProductServiceJuly24.Models.Category;
import com.scaler.ProductServiceJuly24.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductServices implements ProductService{

     private  RestTemplate restTemplate;
     public FakeStoreProductServices(RestTemplate restTemplate) {
         this.restTemplate = restTemplate;
     }

    @Override
    public Product getSingleProduct(Long productId) {
         //call fakestore  productService to fetch the Product with given id. --> http call
         FakeStoreProductDTO fakeStoreProductDTO =  restTemplate.getForObject("https://fakestoreapi.com/products/"+productId, FakeStoreProductDTO.class);

         //convert fakeStoreDTO to Product Object
         return convertFakeProductDTOToProduct(fakeStoreProductDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

      private Product convertFakeProductDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
          Product product = new Product();
          product.setId(fakeStoreProductDTO.getId());
          product.setTitle(fakeStoreProductDTO.getTitle());
          product.setPrice(fakeStoreProductDTO.getPrice());

          Category category = new Category();
          category.setName(fakeStoreProductDTO.getCategory());
          category.setDescription(fakeStoreProductDTO.getDescription());

          product.setCategory(category);

          return product;
      }

}
