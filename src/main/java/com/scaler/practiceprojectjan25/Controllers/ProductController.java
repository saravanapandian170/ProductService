package com.scaler.practiceprojectjan25.Controllers;

import com.scaler.practiceprojectjan25.Dtos.CreateProductRequestDto;
import com.scaler.practiceprojectjan25.Models.Products;
import com.scaler.practiceprojectjan25.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    public ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Products> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable int id){

        Products product = productService.getProductById(id);
        ResponseEntity<Products> response;
        if(product == null){
            response = new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        }
        else response = new ResponseEntity<>(product, HttpStatus.OK);
        return response;
    }

    @PostMapping("/products")
    public Products addProduct(@RequestBody CreateProductRequestDto createProductRequestDto){

        return productService.addProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getCategory(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getImage());
    }
    @GetMapping("/products/category/{category}")
    public List<Products> getProductsByCategory(@PathVariable String category){
        return productService.getProductsByCategory(category);
    }
}
