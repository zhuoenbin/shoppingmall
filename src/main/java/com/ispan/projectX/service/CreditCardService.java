package com.ispan.projectX.service;

import com.ispan.projectX.dao.CreditCardRepository;
import com.ispan.projectX.entity.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository cardRepo;

    //Find user's creditcard information.
    public CreditCard showCreditCard(Integer userId) {

        CreditCard creditCard = cardRepo.findByUserId(userId);

        return creditCard;
    }

}