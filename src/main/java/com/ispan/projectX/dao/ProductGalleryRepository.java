package com.ispan.projectX.dao;

import com.ispan.projectX.entity.Product;
import com.ispan.projectX.entity.ProductGallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductGalleryRepository extends JpaRepository<ProductGallery,Integer> {

    List<ProductGallery> findByProduct(Product product);
}
