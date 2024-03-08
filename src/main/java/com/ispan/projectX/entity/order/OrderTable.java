package com.ispan.projectX.entity.order;

import com.ispan.projectX.entity.Seller;
import com.ispan.projectX.entity.Users;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_table")
public class OrderTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "user_id")
    private Users buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
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
    private LocalDateTime sellerConfirmDate;

    @Column(name = "seller_ship_date")
    private LocalDateTime sellerShipDate;

    @Column(name = "logistics_ship_date")
    private LocalDateTime logisticsShipDate;

    @Column(name = "logistics_arrival_date")
    private LocalDateTime logisticsArrivalDate;

    @Column(name = "buyer_receive_date")
    private LocalDateTime buyerReceiveDate;

    @Column(name = "buyer_confirm_date")
    private LocalDateTime buyerConfirmDate;

    @Column(name = "order_cancel_date")
    private LocalDateTime orderCancelDate;

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

    public OrderTable() {
    }

    public OrderTable(Long orderId, Users buyer, Seller seller, LocalDateTime orderDate, Integer totalPrice, Integer sellerDiscountPrice, Integer sellerCouponPrice, Integer officialDiscountPrice, Integer officialCouponPrice, String discountDescription, String couponDescription, Integer paymentMethod, Integer paymentStatus, LocalDateTime sellerConfirmDate, LocalDateTime sellerShipDate, LocalDateTime logisticsShipDate, LocalDateTime logisticsArrivalDate, LocalDateTime buyerReceiveDate, LocalDateTime buyerConfirmDate, LocalDateTime orderCancelDate, String shippingCompanyName, String city, String district, String address, Integer freight) {
        this.orderId = orderId;
        this.buyer = buyer;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Users getBuyer() {
        return buyer;
    }

    public void setBuyer(Users buyer) {
        this.buyer = buyer;
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

    public LocalDateTime getSellerConfirmDate() {
        return sellerConfirmDate;
    }

    public void setSellerConfirmDate(LocalDateTime sellerConfirmDate) {
        this.sellerConfirmDate = sellerConfirmDate;
    }

    public LocalDateTime getSellerShipDate() {
        return sellerShipDate;
    }

    public void setSellerShipDate(LocalDateTime sellerShipDate) {
        this.sellerShipDate = sellerShipDate;
    }

    public LocalDateTime getLogisticsShipDate() {
        return logisticsShipDate;
    }

    public void setLogisticsShipDate(LocalDateTime logisticsShipDate) {
        this.logisticsShipDate = logisticsShipDate;
    }

    public LocalDateTime getLogisticsArrivalDate() {
        return logisticsArrivalDate;
    }

    public void setLogisticsArrivalDate(LocalDateTime logisticsArrivalDate) {
        this.logisticsArrivalDate = logisticsArrivalDate;
    }

    public LocalDateTime getBuyerReceiveDate() {
        return buyerReceiveDate;
    }

    public void setBuyerReceiveDate(LocalDateTime buyerReceiveDate) {
        this.buyerReceiveDate = buyerReceiveDate;
    }

    public LocalDateTime getBuyerConfirmDate() {
        return buyerConfirmDate;
    }

    public void setBuyerConfirmDate(LocalDateTime buyerConfirmDate) {
        this.buyerConfirmDate = buyerConfirmDate;
    }

    public LocalDateTime getOrderCancelDate() {
        return orderCancelDate;
    }

    public void setOrderCancelDate(LocalDateTime orderCancelDate) {
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
        sb.append(", buyer=").append(buyer);
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

