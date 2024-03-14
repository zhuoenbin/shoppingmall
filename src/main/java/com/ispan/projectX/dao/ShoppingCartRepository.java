package com.ispan.projectX.dao;

import com.ispan.projectX.entity.Product;
import com.ispan.projectX.entity.ShoppingCart;
import com.ispan.projectX.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {

//    @Query("SELECT c FROM ShoppingCart c JOIN FETCH c.users WHERE c.users = :users")
//    List<ShoppingCart> findUsersFromShoppingCart(@Param("users") Users users);

    List<ShoppingCart> findByUsers(Users users);

    @Query("SELECT sc FROM ShoppingCart sc WHERE sc.users = :user AND sc.product = :product")
    ShoppingCart findByUsersAndProduct(@Param("user") Users user, @Param("product") Product product);

    @Query("SELECT sc FROM ShoppingCart sc WHERE sc.shoppingCartId = :shoppingCartId")
    ShoppingCart findByShoppingCartId(@Param("shoppingCartId") Integer shoppingCartId);

    @Query("SELECT sc FROM ShoppingCart sc WHERE sc.product = :product")
    ShoppingCart findByProduct(@Param("product") Product productId);


}
