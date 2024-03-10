package com.ispan.projectX.controller;

import com.ispan.projectX.dao.SellerRepository;
import com.ispan.projectX.entity.Seller;
import com.ispan.projectX.entity.product.Product;
import com.ispan.projectX.entity.product.ProductCategory;
import com.ispan.projectX.entity.product.ProductGalleryCloud;
import com.ispan.projectX.service.ProductCategoryService;
import com.ispan.projectX.service.ProductGalleryCloudService;
import com.ispan.projectX.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ProductController {
    @Autowired //暫時引用
    private SellerRepository sellerRepository;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductGalleryCloudService productGalleryCloudService;

    @GetMapping("/product")
    public List<ProductCategory> getAllCategories(){
        return productCategoryService.listAllCategories();
    }

    @PostMapping("/product/insert")
    public Product createProduct (@RequestBody Product product,
                                  @RequestParam Integer sellerId,
                                  @RequestParam Integer categoryId){

        Seller seller = sellerRepository.findBySellerId(sellerId);
        ProductCategory category = productCategoryService.findByCategoryId(categoryId);

        product.setSeller(seller);
        product.setCategory(category);

        return productService.insertProduct(product);
    }

    @PostMapping("/product/image/insert")
    public ProductGalleryCloud insertProductImg(@RequestParam("productImg")MultipartFile file,
                                                @RequestParam Integer productId,
                                                @RequestParam String imgDescription){
        Product product = productService.findByProductId(productId);

        return productGalleryCloudService.productUploadCloud(file, product, imgDescription);
    }


}
