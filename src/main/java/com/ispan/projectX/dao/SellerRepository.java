package com.ispan.projectX.dao;

import com.ispan.projectX.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

    Seller findBySellerId(Integer sellerId);

    Seller findBySellerName(String Seller);
}
