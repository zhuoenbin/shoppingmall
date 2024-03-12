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

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    //--------R--------

    //列出所有商品
    public List<Product> listAllProduct(){
        return productRepository.findAll();
    }

    //依id尋找商品
    public Product findByProductId(Integer productId){
        return productRepository.findByProductId(productId);
    }

    //依productName尋找產品
    public List<Product> findByProductName(String productName){
        return productRepository.findByProductName(productName);
    }

    //依賣場名(sellerName)尋找商品
    public List<Product> findBySellerName(String sellerName){
        Seller bySellerName = sellerRepository.findBySellerName(sellerName);
        return productRepository.findBySeller(bySellerName);
    }

    //依類別尋找商品
    public List<Product> findByCategoryName(String categoryName){
        ProductCategory byCategoryName = productCategoryRepository.findByCategoryName(categoryName);
        return productRepository.findByCategory(byCategoryName);
    }

    //在商城中依類別尋找商品
    public List<Product> findBySellerAndCategory(String sellerName,String categoryName){
        Seller bySellerName = sellerRepository.findBySellerName(sellerName);
        ProductCategory byCategoryName = productCategoryRepository.findByCategoryName(categoryName);
        return productRepository.findBySellerAndCategory(bySellerName,byCategoryName);
    }




    //--------CU--------

    //上架及更新商品
    public Product insertProduct(Product product){
        return productRepository.save(product);
    }









//尚未完成功能:
    //商品下單更新數量
    //public Product renewProductByOrder();




}