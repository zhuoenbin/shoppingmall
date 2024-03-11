package com.ispan.projectX.dao;

import com.ispan.projectX.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    Users findByUserId(Integer userId);

    Users findUsersByUserEmail(String username);

//    @Query("SELECT u FROM Users u JOIN FETCH u.pushReceiverGroups WHERE u.userId = :id")
//    Users findAllWithPushReceiverGroups(@Param("id") Integer id);
}
