package com.ispan.projectX.dao;


import com.ispan.projectX.entity.ProductGalleryCloud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductGalleryCloudRepository extends JpaRepository<ProductGalleryCloud,Integer> {
    ProductGalleryCloud findByImgId(Integer imgId);
}
