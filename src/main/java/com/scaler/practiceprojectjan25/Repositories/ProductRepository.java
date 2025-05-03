package com.scaler.practiceprojectjan25.Repositories;

import com.scaler.practiceprojectjan25.Models.Category;
import com.scaler.practiceprojectjan25.Models.Products;
import com.scaler.practiceprojectjan25.Projections.ProductWithIdAndPriceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products, Long> {

    @Override
    List<Products> findAll();

    @Override
    Optional<Products> findById(Long id);

    List<Products> findByCategory(Category category);

    List<Products> findAllByCategory_Title(String title);
    List<Products> findAllByCategory_Id(Long categoryId);

    List<Products> findByTitleStartingWithAndIdEqualsAndPriceLessThan(String title, Long id, Double price);

    @Query("select p.id, p.price from Products p where p.category.title = :categoryName")
    List<ProductWithIdAndPriceProjection> getProductWithTitleAndPriceAndGivenCategory(@Param("categoryName") String categoryName);

    @Query(value = "select * from Products p where p.title = :title", nativeQuery = true)
    List<ProductWithIdAndPriceProjection> getIdAndPricesOfAllProductsWithGivenTitle(@Param("title") String title);

}
