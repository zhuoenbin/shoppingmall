package com.ispan.projectX.dao.product;

import com.ispan.projectX.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByProductId(Integer productId);

}
