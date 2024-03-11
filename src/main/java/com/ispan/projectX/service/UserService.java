package com.ispan.projectX.service;

import com.ispan.projectX.dao.UsersRepository;
import com.ispan.projectX.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepo;

    //show profile
    public Users showProfile(Integer userId) {

        Users profile = userRepo.findByUserId(userId);

        return profile;
    }

    //edit profile
    public Users editProfile(Users user) {

        return userRepo.save(user);
    }
}
