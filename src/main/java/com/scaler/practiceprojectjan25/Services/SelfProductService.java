package com.scaler.practiceprojectjan25.Services;

import com.scaler.practiceprojectjan25.Exceptions.ProductNotFoundException;
import com.scaler.practiceprojectjan25.Models.Category;
import com.scaler.practiceprojectjan25.Models.Products;
import com.scaler.practiceprojectjan25.Projections.ProductWithIdAndPriceProjection;
import com.scaler.practiceprojectjan25.Repositories.CategoryRepository;
import com.scaler.practiceprojectjan25.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Products> getAllProducts() {
        List<Products> products = productRepository.findAll();
        return products;
    }

    @Override
    public Products getProductById(long id) throws ProductNotFoundException {

        Optional<Products> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException(
                    "Product with the given id is not found");
        }
        return product.get();
    }

    @Override
    public Products addProduct(String title, String description, String category, double price, String image) {

        Products product = new Products();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(image);

        Category category1 = categoryRepository.findByTitle(category);

        if(category1 == null){
            Category newCategory = new Category();
            newCategory.setTitle(category);
            product.setCategory(newCategory);
        }else{
            product.setCategory(category1);
        }
        Products newProduct = productRepository.save(product);
        return newProduct;
    }

    @Override
    public List<Products> getProductsByCategory(String Category) {
        return List.of();
    }

    @Override
    public List<Products> getProductsByTitle(String Title) {
        List<ProductWithIdAndPriceProjection> priceProjections = productRepository.
                getIdAndPricesOfAllProductsWithGivenTitle(Title);
        List<Products> products = new ArrayList<>();
        for(ProductWithIdAndPriceProjection projection : priceProjections){
            Products product = new Products();
            product.setId(projection.getId());
            product.setPrice(projection.getPrice());
            products.add(product);
        }
        return products;
    }
}
