package com.ispan.projectX.dao.product;


import com.ispan.projectX.entity.product.ProductGallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductGalleryRepository extends JpaRepository<ProductGallery,Integer> {
    ProductGallery findByImgId(Integer imgId);
}
