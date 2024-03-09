package com.ispan.projectX.dao;

import com.ispan.projectX.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByProductId(Integer productId);

}
