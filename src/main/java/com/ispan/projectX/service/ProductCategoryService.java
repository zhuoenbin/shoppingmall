package com.ispan.projectX.service;

import com.ispan.projectX.dao.product.ProductCategoryRepository;
import com.ispan.projectX.entity.product.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    public ProductCategory insertCategory(ProductCategory category){
        return categoryRepository.save(category);
    }

    public ProductCategory findByCategoryId(Integer categoryId){
        return categoryRepository.findByCategoryId(categoryId);
    }

    public List<ProductCategory> listAllCategories(){
        return categoryRepository.findAll();
    }

}
