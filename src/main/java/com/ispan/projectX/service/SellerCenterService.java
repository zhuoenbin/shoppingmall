package com.ispan.projectX.service;

import com.ispan.projectX.dao.SellerRepository;
import com.ispan.projectX.dao.UsersRepository;
import com.ispan.projectX.entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class SellerCenterService {

    @Autowired
    private SellerRepository sellerRepo;

    @Autowired
    private UsersRepository userRepo;

    public void showSellerProfile(Model model, Integer userId,Seller seller){
        Seller sellerProfile = sellerRepo.findSellerIdByUserId(userId);
        model.addAttribute("sellerprofile",sellerProfile);
    }
}
