package com.ispan.projectX.dao;

import com.ispan.projectX.entity.CreditCard;
import com.ispan.projectX.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard,Integer> {
    List<CreditCard> findByUsers(Users user);
}
