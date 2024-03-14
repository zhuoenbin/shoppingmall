package com.ispan.projectX.Service;

import com.ispan.projectX.dao.UsersRepository;
import com.ispan.projectX.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UsersRepository uRepository;

    public Users findById(Integer userId) {
        return uRepository.findByUserId(userId);
    }

}
