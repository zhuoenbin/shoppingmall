package com.ispan.projectX.service;

import com.ispan.projectX.dao.order.OrderDetailRepository;
import com.ispan.projectX.dao.order.OrderTableRepository;
import com.ispan.projectX.dao.product.ProductRepository;
import com.ispan.projectX.entity.order.OrderDetail;
import com.ispan.projectX.entity.order.OrderTable;
import com.ispan.projectX.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;


    public OrderDetail findByOrderDetailId(Integer orderDetailId){
        return orderDetailRepository.findByOrderDetailId(orderDetailId);
    }

    //某訂單買了哪些東西
    public List<OrderDetail> findByOrderId(Integer orderId){
        OrderTable order = orderService.findByOrderId(orderId);
        return orderDetailRepository.findByOrder(order);
    }

    //看某貨的訂單量==>可以算銷量
    public List<OrderDetail> findByProductId(Integer productId){
        Product product = productService.findByProductId(productId);
        return orderDetailRepository.findByProduct(product);
    }
}
