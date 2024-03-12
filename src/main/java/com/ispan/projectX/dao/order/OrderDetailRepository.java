package com.ispan.projectX.dao.order;


import com.ispan.projectX.entity.order.OrderDetail;
import com.ispan.projectX.entity.order.OrderTable;
import com.ispan.projectX.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    OrderDetail findByOrderDetailId(Integer orderDetailId);

    //某訂單買了哪些東西
    @Query("from OrderDetail where order = ?1")
    List<OrderDetail> findByOrder(OrderTable order);

    //看某貨的訂單量==>可以算銷量
    @Query("from OrderDetail where product = ?1")
    List<OrderDetail> findByProduct(Product product);
}
