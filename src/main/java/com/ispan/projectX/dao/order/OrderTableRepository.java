package com.ispan.projectX.dao.order;

import com.ispan.projectX.entity.order.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTableRepository extends JpaRepository<OrderTable,Integer> {

    OrderTable findByOrderId(Integer orderId);

}
