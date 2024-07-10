package com.scaler.ProductServiceJuly24.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{

    private String title;
    private double price;
    private Category category;
}
