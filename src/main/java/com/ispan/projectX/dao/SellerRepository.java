package com.ispan.projectX.dao;

import com.ispan.projectX.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

    Seller findBySellerId(Integer sellerId);

    Seller findBySellerName(String Seller);

    //用來載入Seller裡面的pushReceiverGroups，後續可直接用.getPushReceiverGroups()
//    @Query("SELECT DISTINCT s FROM Seller s JOIN FETCH s.pushReceiverGroups WHERE s.id = :sellerId")
//    Seller findByPushReceiverGroupsWithSellerId(Integer sellerId);
}
