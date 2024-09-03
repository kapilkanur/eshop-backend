package com.kk.eshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    /**
     * List products.
     */
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    /**
     * Category constructor.
     * @param categoryName category name
     */
    public Category(final String categoryName) {
        this.name = categoryName;
    }
}
