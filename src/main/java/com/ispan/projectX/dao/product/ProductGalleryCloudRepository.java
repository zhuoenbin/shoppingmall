package com.ispan.projectX.dao.product;


import com.ispan.projectX.entity.product.ProductGalleryCloud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductGalleryCloudRepository extends JpaRepository<ProductGalleryCloud,Integer> {
    ProductGalleryCloud findByImgId(Integer imgId);
}
