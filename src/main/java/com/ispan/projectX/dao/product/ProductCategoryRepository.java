package com.ispan.projectX.dao.product;

import com.ispan.projectX.entity.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    ProductCategory findByCategoryId(Integer categoryId);

    ProductCategory findByCategoryName(String categoryName);
}
