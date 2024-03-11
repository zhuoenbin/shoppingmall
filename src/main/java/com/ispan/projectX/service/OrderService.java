package com.ispan.projectX.service;

import com.ispan.projectX.dao.order.OrderTableRepository;
import com.ispan.projectX.entity.Seller;
import com.ispan.projectX.entity.Users;
import com.ispan.projectX.entity.order.OrderTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderTableRepository orderTableRepository;

    public OrderTable findByOrderId(Integer orderId){
        return orderTableRepository.findByOrderId(orderId);
    }

    public List<OrderTable> findUserOrders(Users user){
        return orderTableRepository.findByUser(user);
    }

    public List<OrderTable> findSellerOrders(Seller seller){
        return orderTableRepository.findBySeller(seller);
    }
}
