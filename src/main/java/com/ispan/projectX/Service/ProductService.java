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

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository pRepository;

    public Page<Product> findByPage(Integer pageNumber) {

        Pageable pgb = PageRequest.of(pageNumber-1, 5, Sort.Direction.DESC, "listingDate");
        Page<Product> page = pRepository.findAll(pgb);

        return page;

    }

}
