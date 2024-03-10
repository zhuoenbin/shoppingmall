package com.ispan.projectX.service;

import com.cloudinary.Cloudinary;
import com.ispan.projectX.dao.SellerRepository;
import com.ispan.projectX.dao.product.ProductCategoryRepository;
import com.ispan.projectX.dao.product.ProductRepository;
import com.ispan.projectX.entity.Seller;
import com.ispan.projectX.entity.product.Product;
import com.ispan.projectX.entity.product.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product findByProductId(Integer productId){
        return productRepository.findByProductId(productId);
    }

    public Product insertProduct(Product product){
        return productRepository.save(product);
    }








}
