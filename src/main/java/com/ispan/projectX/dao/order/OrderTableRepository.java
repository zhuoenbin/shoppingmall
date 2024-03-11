package com.ispan.projectX.dao.order;

import com.ispan.projectX.entity.Seller;
import com.ispan.projectX.entity.Users;
import com.ispan.projectX.entity.order.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderTableRepository extends JpaRepository<OrderTable,Integer> {

    OrderTable findByOrderId(Integer orderId);

    @Query("from OrderTable where user = ?1")
    List<OrderTable> findByUser(Users user);

    @Query("from OrderTable where seller = ?1")
    List<OrderTable> findBySeller(Seller seller);

}
