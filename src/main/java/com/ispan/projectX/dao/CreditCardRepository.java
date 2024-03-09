package com.ispan.projectX.dao;

import com.ispan.projectX.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard,Integer> {

    CreditCard findByUserId(Integer userId);

    CreditCard findByCreditId(Long creditId);

}
