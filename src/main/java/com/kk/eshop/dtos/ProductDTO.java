package com.kk.eshop.dtos;

import com.kk.eshop.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private int quantityInInventory;
    private Category category;
}
