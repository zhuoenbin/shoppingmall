package com.ispan.projectX.dao;

import com.ispan.projectX.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    ProductCategory findByCategoryId(Integer categoryId);
}
