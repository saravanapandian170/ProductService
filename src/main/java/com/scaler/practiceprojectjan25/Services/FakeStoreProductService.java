package com.scaler.practiceprojectjan25.Services;

import com.scaler.practiceprojectjan25.Dtos.FakeStoreProductDto;
import com.scaler.practiceprojectjan25.Models.Products;
import com.scaler.practiceprojectjan25.Projections.ProductWithIdAndPriceProjection;
import com.scaler.practiceprojectjan25.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    private RestTemplate restTemplate;


    public FakeStoreProductService(RestTemplate restTemplate, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.productRepository = productRepository;
    }

    @Override
    public List<Products> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Products> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Products product = fakeStoreProductDto.toProduct();
            products.add(product);
        }
        return products;
    }



    @Override
    public Products getProductById(long id) {

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();

        if(fakeStoreProductDto == null){
            return null;
        }
        Products response = fakeStoreProductDto.toProduct();
        return response;
    }

    @Override
    public Products addProduct(String title,
                               String description,
                               String Category,
                               double price,
                               String image) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setCategory(Category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);

        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.postForObject("https://fakestoreapi.com/products/",
                fakeStoreProductDto, FakeStoreProductDto.class);

        return fakeStoreProductDto1.toProduct();
    }

    @Override
    public List<Products> getProductsByCategory(String category) {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + category,
                FakeStoreProductDto[].class
                );
        if(fakeStoreProductDtos == null || fakeStoreProductDtos.length == 0){
            return null;
        }
        List<Products> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(fakeStoreProductDto.toProduct());
        }
        return products;
    }

    @Override
    public List<Products> getProductsByTitle(String Title) {
        return List.of();
    }
}
