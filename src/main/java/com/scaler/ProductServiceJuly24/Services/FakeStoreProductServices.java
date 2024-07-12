package com.scaler.ProductServiceJuly24.Services;

import com.scaler.ProductServiceJuly24.DTO.FakeStoreProductDTO;
import com.scaler.ProductServiceJuly24.Models.Category;
import com.scaler.ProductServiceJuly24.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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

         // call fakestore productService to fetch all the products  ---> http call
        FakeStoreProductDTO[] allproducts = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
          List<Product> products = new ArrayList<>();
          for (FakeStoreProductDTO fakeStoreProductDTO : allproducts) {
              products.add(convertFakeProductDTOToProduct(fakeStoreProductDTO));
          }

        return products;
    }


    @Override
    public List<Product> getProductByLimit(int limit) {

        // call fakestore productService to fetch the products by Limit ---> http call
        FakeStoreProductDTO[] productsbylimit =  restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDTO[].class);
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < limit; i++) {

            products.add(convertFakeProductDTOToProduct(productsbylimit[i]));
        }

        return products;
    }

    @Override
    public List<Product> getAllProductInDesc(String desc) {
        FakeStoreProductDTO[] getAllProductinDesc = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDTO[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO : getAllProductinDesc){
            products.add(convertFakeProductDTOToProduct(fakeStoreProductDTO));
        }
        if(desc.equalsIgnoreCase("desc")){
            return ProductsInDescendingOrder(products);
        }

        return products;
    }

    private List<Product> ProductsInDescendingOrder(List<Product> products) {
        // Sort products in descending order by id
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Long.compare(p2.getId(), p1.getId());
            }
        });
        return products;
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
