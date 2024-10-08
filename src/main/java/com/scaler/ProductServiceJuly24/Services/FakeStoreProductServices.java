package com.scaler.ProductServiceJuly24.Services;

import com.scaler.ProductServiceJuly24.DTO.FakeStoreProductDTO;
import com.scaler.ProductServiceJuly24.Models.Category;
import com.scaler.ProductServiceJuly24.Models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public List<String> getAllProductCategory() {
         FakeStoreProductDTO[] getAllProduct = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDTO[].class);
         List<String> productNames = new ArrayList<>();
          for(FakeStoreProductDTO fakeStoreProductDTO : getAllProduct){
              productNames.add(fakeStoreProductDTO.getCategory());
          }
         return productNames.stream().distinct().collect(Collectors.toList());
        //removing duplicates in above line from the help of stream API
    }

    @Override
    public List<Product> getProductByCategory(String categoryName) {
         FakeStoreProductDTO[] getCategory = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDTO[].class);
         List<Product> products = new ArrayList<>();
          for(FakeStoreProductDTO fakeStoreProductDTO: getCategory)
          {
              if(fakeStoreProductDTO.getCategory().equals(categoryName)) {
                  products.add(convertFakeProductDTOToProduct(fakeStoreProductDTO));
              }
          }
        return products;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
    //patch call

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseType = new HttpMessageConverterExtractor(FakeStoreProductDTO.class,restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/" + id,HttpMethod.PATCH, requestCallback,responseType);
        return convertFakeProductDTOToProduct(response);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {

         RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDTO.class);
         HttpMessageConverterExtractor<FakeStoreProductDTO> responseType = new HttpMessageConverterExtractor(FakeStoreProductDTO.class,restTemplate.getMessageConverters());
         FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/" + productId, HttpMethod.PUT, requestCallback,responseType);

        return convertFakeProductDTOToProduct(response);
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
