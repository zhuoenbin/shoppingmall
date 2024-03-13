package com.ispan.projectX.service;

import com.ispan.projectX.dao.SellerRepository;
import com.ispan.projectX.dao.ShoppingCartRepository;
import com.ispan.projectX.dao.UsersRepository;
import com.ispan.projectX.dao.order.OrderDetailRepository;
import com.ispan.projectX.dao.order.OrderTableRepository;
import com.ispan.projectX.dao.product.ProductRepository;
import com.ispan.projectX.entity.Seller;
import com.ispan.projectX.entity.ShoppingCart;
import com.ispan.projectX.entity.Users;
import com.ispan.projectX.entity.order.OrderDetail;
import com.ispan.projectX.entity.order.OrderTable;
import com.ispan.projectX.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderSystemService {
    @Autowired
    private OrderTableRepository orderTableRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private SellerRepository sellerRepository;


    ///////////////////Order 訂單///////////////////////
    public OrderTable findByOrderId(Integer orderId){
        return orderTableRepository.findByOrderId(orderId);
    }

    //取消訂單
    public OrderTable OrderCancel(Integer orderId){
        OrderTable order = orderTableRepository.findByOrderId(orderId);
        order.setOrderCancelDate(new Date());
        return orderTableRepository.save(order);
    }


    //查看使用者所有訂單
    public List<OrderTable> findUserOrders(Integer userId){
        Users user = usersRepository.findByUserId(userId);
        return orderTableRepository.findByUser(user);
    }

    //查看賣家所有訂單
    public List<OrderTable> findSellerOrders(Integer sellerId){
        Seller seller = sellerRepository.findBySellerId(sellerId);
        return orderTableRepository.findBySeller(seller);
    }

    //賣家確認訂單
    public OrderTable orderConfirm(Integer orderId){
        OrderTable order = orderTableRepository.findByOrderId(orderId);
        order.setSellerConfirmDate(new Date());
        return orderTableRepository.save(order);
    }

    //賣家出貨
    public OrderTable sellerSend(Integer orderId){
        OrderTable order = orderTableRepository.findByOrderId(orderId);
        order.setSellerShipDate(new Date());
        return orderTableRepository.save(order);
    }

    ///////////////////OrderDetail訂單詳細/////////////////////////

    public OrderDetail findByOrderDetailId(Integer orderDetailId){
        return orderDetailRepository.findByOrderDetailId(orderDetailId);
    }

    //某訂單買了哪些東西
    public List<OrderDetail> findDetailsByOrderId(Integer orderId){
        OrderTable order = orderTableRepository.findByOrderId(orderId);
        return orderDetailRepository.findByOrder(order);
    }

    //看某貨的訂單量==>可以算銷量
    public List<OrderDetail> findByProductId(Integer productId){
        Product product = productRepository.findByProductId(productId);
        return orderDetailRepository.findByProduct(product);
    }

    ///////////////////////建立訂單///////////////////////

    //Step 1.建立訂單預覽
    public HashMap<Integer, List<OrderDetail>> createPreviewOrders (List<ShoppingCart> shoppingCartList){
        HashMap<Integer, List<OrderDetail>> sellerOrderMap = new HashMap<>();


        //依照sellerId區分訂單 放到不同的
        for(ShoppingCart shoppingCart:shoppingCartList){
            Product product = productRepository.findByProductId(shoppingCart.getProduct().getProductId());
            if(product==null){
                return null;
            }
            //得到sellerId
            Integer sellerId = product.getSeller().getSellerId();

            //先創建orderDetail
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(shoppingCart.getQuantity());
            //--價錢

            Integer unitPrice = shoppingCart.getUnitPrice();
            orderDetail.setPrice(unitPrice);

            List<OrderDetail> orderDetailList = sellerOrderMap.computeIfAbsent(sellerId, k -> new ArrayList<>());
            orderDetailList.add(orderDetail);

        }
        return sellerOrderMap;
    }

    //Step2.建立訂單
    public List<OrderTable> createOrders(HashMap<Integer, List<OrderDetail>> sellerOrderMap,
                                         Integer userId,
                                         HashMap<Integer,Integer> priceList, //在前端算總價
                                         Integer paymentMethod,
                                         String shippingCompanyName,
                                         String city,String district, String address){
        List<OrderTable> orderSaved = new ArrayList<>();

        for (Map.Entry<Integer, List<OrderDetail>> entry : sellerOrderMap.entrySet()){
            Integer sellerId = entry.getKey();
            List<OrderDetail> orderDetailList = entry.getValue();
            Integer orderPrice = priceList.get(sellerId);

            OrderTable order = new OrderTable();
            order.setSeller(sellerRepository.findBySellerId(sellerId));
            order.setUser(usersRepository.findByUserId(userId));
            order.setTotalPrice(orderPrice);
            order.setPaymentMethod(paymentMethod);
            order.setShippingCompanyName(shippingCompanyName);
            order.setFreight(60);
            order.setCity(city);
            order.setDistrict(district);
            order.setAddress(address);

            //創建訂單
            OrderTable saved = orderTableRepository.save(order);
            orderSaved.add(saved);

            //創建訂單詳細
            for(OrderDetail orderDetail:orderDetailList){
                orderDetail.setOrder(saved);
                orderDetailRepository.save(orderDetail);
            }
        }

        return orderSaved;

    }

}
