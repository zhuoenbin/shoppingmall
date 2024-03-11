package com.ispan.projectX.controller;

import com.ispan.projectX.entity.Users;
import com.ispan.projectX.service.CreditCardService;
import com.ispan.projectX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpSession;

import java.util.Date;

@Controller
public class UserCenterController {

    @Autowired
    private UserService uService;

    @Autowired
    private CreditCardService cardService;

    @GetMapping("/usercenter/userProfile")
    public String goUserProfile(Model model,HttpSession hsession) {
//

        Users user = new Users();

        user.setFirstName("俞");
        user.setLastName("建誠");
        user.setBirthDate(new Date());

        model.addAttribute("user",user);

        return "usercenter/userProfilePage";
    }

    @GetMapping("/usercenter/userPayment")
    public String goUserPayment(Model model,HttpSession hsession) {
        Integer userId = (Integer)hsession.getAttribute("userId");

        cardService.showCreditCard(userId);

        return "usercenter/userPaymentPage";
    }



    @PostMapping("/usercenter/editProfile")
    public String editUserProfile(@RequestParam("userfirstname") String uFirstName,
                                  @RequestParam("userlastname") String uLastName,
                                  @RequestParam("usergender") String uGender,
                                  @RequestParam("userbirthday") Date uBirthDay) {

        Users user = new Users();

        user.setFirstName(uFirstName);
        user.setLastName(uLastName);
        user.setUserGender(uGender);
        user.setBirthDate(uBirthDay);

        uService.editProfile(user);

        return "usercenter/userProfilePage";
    }


}