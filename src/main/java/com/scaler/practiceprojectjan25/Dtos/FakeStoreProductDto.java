package com.scaler.practiceprojectjan25.Dtos;

import com.scaler.practiceprojectjan25.Models.Category;
import com.scaler.practiceprojectjan25.Models.Products;

public class FakeStoreProductDto {

    private long id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;

    public Products toProduct(){
        Products product = new Products();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(image);

        Category category1 = new Category();
        category1.setTitle(category);
        product.setCategory(category1);
        return product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
