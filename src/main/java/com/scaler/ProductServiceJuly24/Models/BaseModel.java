package com.scaler.ProductServiceJuly24.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseModel {
    private long id;
    private Data createdAt;
    private Data updatedAt;
}
