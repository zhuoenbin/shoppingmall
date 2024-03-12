package com.ispan.projectX.service;

import com.ispan.projectX.dao.CreditCardRepository;
import com.ispan.projectX.dao.UsersRepository;
import com.ispan.projectX.entity.CreditCard;
import com.ispan.projectX.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class UserCenterService {

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private CreditCardRepository cardRepo;

    //show profile
    public void showProfile(Model model,Integer userId) {

        Users profile = userRepo.findByUserId(userId);

        model.addAttribute("userprofile",profile);
    }

    //Find user's creditcard information.
    public void showCreditCard(Model model,Integer userId) {

        CreditCard creditCard = cardRepo.findByUserId(userId);

        model.addAttribute(creditCard);
    }

    //edit profile
    public void editProfile(Users user) {
        userRepo.save(user);
    }

}
