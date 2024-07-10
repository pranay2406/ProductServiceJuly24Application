package com.scaler.ProductServiceJuly24.DTO;

import com.scaler.ProductServiceJuly24.Models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO
{
   private long id;
   private String title;
   private double price;
   private String  category;
   private String description;
   private String image;
}


