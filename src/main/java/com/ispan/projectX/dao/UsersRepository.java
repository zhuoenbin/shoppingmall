package com.ispan.projectX.dao;

import com.ispan.projectX.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    Users findByUserId(Integer userId);

    Users findBankIdAccount1ByUserId(Integer userId);

    Users findBankAccount1ByUserId(Integer userId);
}
