// CHECKSTYLE:OFF
package com.kk.eshop.service.product;

import com.kk.eshop.dtos.ProductDTO;
import com.kk.eshop.dtos.ProductUpdateDTO;
import com.kk.eshop.model.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(ProductDTO productDTO);
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrandName(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    void deleteProductById(Long productId);
    Product updateProductById(ProductUpdateDTO product, Long productId);
    Long countProductsByBrandAndName(String brand, String name);
}
