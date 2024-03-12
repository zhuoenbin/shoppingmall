package com.ispan.projectX.dao;

import com.ispan.projectX.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

    Seller findBySellerId(Integer sellerId);

    @Query("SELECT s.sellerId FROM Seller s JOIN s.user u ON u.userId = :userId")
    Seller findSellerIdByUserId(@Param("userId") Integer userId);
}
