package com.ispan.projectX.dao.product;

import com.ispan.projectX.entity.Seller;
import com.ispan.projectX.entity.product.Product;
import com.ispan.projectX.entity.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByProductId(Integer productId);

    List<Product> findByProductName(String productName);

    @Query("from Product where seller = ?1")
    List<Product> findBySeller(Seller seller);

    @Query("from Product where category = ?1 ")
    List<Product> findByCategory(ProductCategory category);

    @Query("from Product where seller = ?1 and category = ?2")
    List<Product> findBySellerAndCategory(Seller seller,ProductCategory category);



}
