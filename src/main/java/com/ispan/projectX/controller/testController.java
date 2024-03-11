package com.ispan.projectX.controller;

import com.ispan.projectX.dao.order.OrderTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @Autowired
    private OrderTableRepository orderTableRepository;

    @GetMapping("/getOrder/{orderId}")
    public String testOrder(@PathVariable Integer orderId){
        return orderTableRepository.findByOrderId(orderId).toString();
    }
}
