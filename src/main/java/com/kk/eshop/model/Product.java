package com.kk.eshop.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private int quantityInInventory;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    /**
     * Product constructor.
     * @param productName product name
     * @param productBrand product brand
     * @param productPrice product price
     * @param productQuantityInInventory product quantity in inventory
     * @param productDescription product description
     * @param productCategory product category
     */
    public Product(final String productName,
                   final String productBrand,
                   final BigDecimal productPrice,
                   final int productQuantityInInventory,
                   final String productDescription,
                   final Category productCategory) {
    }
}
