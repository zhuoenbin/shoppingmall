package com.ispan.projectX.Service;

import com.ispan.projectX.dao.ProductRepository;
import com.ispan.projectX.dao.ShoppingCartRepository;
import com.ispan.projectX.entity.Product;
import com.ispan.projectX.entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository pRepository;

    public void insert(Product product) {
        pRepository.save(product);
    }

    public Page<Product> findByPage(Integer pageNumber) {

        Pageable pgb = PageRequest.of(pageNumber-1, 5, Sort.Direction.DESC, "listingDate");
        // 修改只有 Approved 狀態的商品能呈現
        Page<Product> page = pRepository.findAll(pgb);

        return page;
    }

//    public List<Product> findStatusAndStock(Integer pageNumber) {
//
//        List<Product> products = pRepository.findAll();
//        List<Product> approved = new ArrayList<>();
//
//        // 當這個商品的存貨不等於 0 且商品狀態為 Approved 才會呈現在商品主頁
//        for(Product product : products) {
//            if(product.getStock() != 0 && product.getProductStatus().equals("Approved")) {
//                approved.add(product);
//            }
//        }
//
//        return approved;
//    };

    public List<Product> findAll() {
        return pRepository.findAll();
    }

    public Product findById(Integer productId) {
        return pRepository.findByProductId(productId);
    }

    public List<Product> findVerify() {
        List<Product> all = pRepository.findAll();
        List<Product> verify = new ArrayList<>();

        for(Product product : all) {
            if(!product.getProductStatus().equals("Approved")) {
                verify.add(product);
            }
        }

        return verify;
    }

}
