package com.ispan.projectX.entity.order;

import com.ispan.projectX.entity.ProductGallery;
import com.ispan.projectX.entity.Seller;
import com.ispan.projectX.entity.Users;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_table")
public class OrderTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "buyer_id")
    private Users user;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "seller_discount_price")
    private Integer sellerDiscountPrice;

    @Column(name = "seller_coupon_price")
    private Integer sellerCouponPrice;

    @Column(name = "official_discount_price")
    private Integer officialDiscountPrice;

    @Column(name = "official_coupon_price")
    private Integer officialCouponPrice;

    @Column(name = "discount_description")
    private String discountDescription;

    @Column(name = "coupon_description")
    private String couponDescription;

    @Column(name = "payment_method")
    private Integer paymentMethod;

    @Column(name = "payment_status")
    private Integer paymentStatus;

    @Column(name = "seller_confirm_date")
    private Date sellerConfirmDate;

    @Column(name = "seller_ship_date")
    private Date sellerShipDate;

    @Column(name = "logistics_ship_date")
    private Date logisticsShipDate;

    @Column(name = "logistics_arrival_date")
    private Date logisticsArrivalDate;

    @Column(name = "buyer_receive_date")
    private Date buyerReceiveDate;

    @Column(name = "buyer_confirm_date")
    private Date buyerConfirmDate;

    @Column(name = "order_cancel_date")
    private Date orderCancelDate;

    @Column(name = "shipping_company_name")
    private String shippingCompanyName;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "address")
    private String address;

    @Column(name = "freight")
    private Integer freight;

    @OneToMany(mappedBy = "order",
                fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    public List<OrderDetail> getOrderDetail() {
        return orderDetails;
    }

    public void setOrderDetail(List<OrderDetail> productGallery) {
        this.orderDetails = orderDetails;
    }

    public void add(OrderDetail tmpOrderDetail){
        if(orderDetails==null){
            orderDetails = new ArrayList<>();
        }
        orderDetails.add(tmpOrderDetail);

        tmpOrderDetail.setOrder(this);
    }

    public OrderTable() {
    }

    public OrderTable(Users user, Seller seller, LocalDateTime orderDate, Integer totalPrice, Integer sellerDiscountPrice, Integer sellerCouponPrice, Integer officialDiscountPrice, Integer officialCouponPrice, String discountDescription, String couponDescription, Integer paymentMethod, Integer paymentStatus, Date sellerConfirmDate, Date sellerShipDate, Date logisticsShipDate, Date logisticsArrivalDate, Date buyerReceiveDate, Date buyerConfirmDate, Date orderCancelDate, String shippingCompanyName, String city, String district, String address, Integer freight) {
        this.user = user;
        this.seller = seller;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.sellerDiscountPrice = sellerDiscountPrice;
        this.sellerCouponPrice = sellerCouponPrice;
        this.officialDiscountPrice = officialDiscountPrice;
        this.officialCouponPrice = officialCouponPrice;
        this.discountDescription = discountDescription;
        this.couponDescription = couponDescription;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.sellerConfirmDate = sellerConfirmDate;
        this.sellerShipDate = sellerShipDate;
        this.logisticsShipDate = logisticsShipDate;
        this.logisticsArrivalDate = logisticsArrivalDate;
        this.buyerReceiveDate = buyerReceiveDate;
        this.buyerConfirmDate = buyerConfirmDate;
        this.orderCancelDate = orderCancelDate;
        this.shippingCompanyName = shippingCompanyName;
        this.city = city;
        this.district = district;
        this.address = address;
        this.freight = freight;
    }


    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        if(orderDetails == null) {
            orderDetails = new ArrayList<>();
        }
        orderDetails.add(orderDetail);
        orderDetail.setOrder(this);
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getSellerDiscountPrice() {
        return sellerDiscountPrice;
    }

    public void setSellerDiscountPrice(Integer sellerDiscountPrice) {
        this.sellerDiscountPrice = sellerDiscountPrice;
    }

    public Integer getSellerCouponPrice() {
        return sellerCouponPrice;
    }

    public void setSellerCouponPrice(Integer sellerCouponPrice) {
        this.sellerCouponPrice = sellerCouponPrice;
    }

    public Integer getOfficialDiscountPrice() {
        return officialDiscountPrice;
    }

    public void setOfficialDiscountPrice(Integer officialDiscountPrice) {
        this.officialDiscountPrice = officialDiscountPrice;
    }

    public Integer getOfficialCouponPrice() {
        return officialCouponPrice;
    }

    public void setOfficialCouponPrice(Integer officialCouponPrice) {
        this.officialCouponPrice = officialCouponPrice;
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getSellerConfirmDate() {
        return sellerConfirmDate;
    }

    public void setSellerConfirmDate(Date sellerConfirmDate) {
        this.sellerConfirmDate = sellerConfirmDate;
    }

    public Date getSellerShipDate() {
        return sellerShipDate;
    }

    public void setSellerShipDate(Date sellerShipDate) {
        this.sellerShipDate = sellerShipDate;
    }

    public Date getLogisticsShipDate() {
        return logisticsShipDate;
    }

    public void setLogisticsShipDate(Date logisticsShipDate) {
        this.logisticsShipDate = logisticsShipDate;
    }

    public Date getLogisticsArrivalDate() {
        return logisticsArrivalDate;
    }

    public void setLogisticsArrivalDate(Date logisticsArrivalDate) {
        this.logisticsArrivalDate = logisticsArrivalDate;
    }

    public Date getBuyerReceiveDate() {
        return buyerReceiveDate;
    }

    public void setBuyerReceiveDate(Date buyerReceiveDate) {
        this.buyerReceiveDate = buyerReceiveDate;
    }

    public Date getBuyerConfirmDate() {
        return buyerConfirmDate;
    }

    public void setBuyerConfirmDate(Date buyerConfirmDate) {
        this.buyerConfirmDate = buyerConfirmDate;
    }

    public Date getOrderCancelDate() {
        return orderCancelDate;
    }

    public void setOrderCancelDate(Date orderCancelDate) {
        this.orderCancelDate = orderCancelDate;
    }

    public String getShippingCompanyName() {
        return shippingCompanyName;
    }

    public void setShippingCompanyName(String shippingCompanyName) {
        this.shippingCompanyName = shippingCompanyName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderTable{");
        sb.append("orderId=").append(orderId);
        sb.append(", user=").append(user);
        sb.append(", seller=").append(seller);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", sellerDiscountPrice=").append(sellerDiscountPrice);
        sb.append(", sellerCouponPrice=").append(sellerCouponPrice);
        sb.append(", officialDiscountPrice=").append(officialDiscountPrice);
        sb.append(", officialCouponPrice=").append(officialCouponPrice);
        sb.append(", discountDescription='").append(discountDescription).append('\'');
        sb.append(", couponDescription='").append(couponDescription).append('\'');
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append(", paymentStatus=").append(paymentStatus);
        sb.append(", sellerConfirmDate=").append(sellerConfirmDate);
        sb.append(", sellerShipDate=").append(sellerShipDate);
        sb.append(", logisticsShipDate=").append(logisticsShipDate);
        sb.append(", logisticsArrivalDate=").append(logisticsArrivalDate);
        sb.append(", buyerReceiveDate=").append(buyerReceiveDate);
        sb.append(", buyerConfirmDate=").append(buyerConfirmDate);
        sb.append(", orderCancelDate=").append(orderCancelDate);
        sb.append(", shippingCompanyName='").append(shippingCompanyName).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", district='").append(district).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", freight=").append(freight);
        sb.append('}');
        return sb.toString();
    }
}

