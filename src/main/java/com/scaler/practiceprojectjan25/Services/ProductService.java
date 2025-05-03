package com.scaler.practiceprojectjan25.Services;

import com.scaler.practiceprojectjan25.Models.Products;

import java.util.List;

public interface ProductService {
    List<Products> getAllProducts();
    Products getProductById(long id);
    Products addProduct(String title,
                        String description,
                        String Category,
                        double price,
                        String image);
    List<Products>getProductsByCategory(String Category);
    List<Products> getProductsByTitle(String Title);
}
