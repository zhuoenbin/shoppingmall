package com.ispan.projectX.dao;


import com.ispan.projectX.entity.ProductGallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductGalleryRepository extends JpaRepository<ProductGallery,Integer> {
    ProductGallery findByImgId(Integer imgId);
}
