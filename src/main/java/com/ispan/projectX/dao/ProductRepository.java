package com.ispan.projectX.dao;//package com.ispan.projectX.dao;

import com.ispan.projectX.entity.Product;
import com.ispan.projectX.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

      Product findByProductId(Integer productId);

      List<Product> findBySeller(Seller seller);
}
