package com.ispan.projectX.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "seller_user_id")
    private Integer sellerUserId;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "seller_image", length = 1000)
    private String sellerImage;

    @Column(name = "seller_image_public_id", length = 500)
    private String sellerImagePublicId;

    @Column(name = "seller_introduce", length = 500)
    private String sellerIntroduce;

    @Column(name = "join_time")
    private Date joinTime;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "seller_violation_count")
    private Integer sellerViolationCount;

    @Column(name = "bank_id_account1", length = 10)
    private String bankIdAccount1;

    @Column(name = "bank_account1", length = 50)
    private String bankAccount1;

    @Column(name = "bank_id_account2", length = 10)
    private String bankIdAccount2;

    @Column(name = "bank_account2", length = 50)
    private String bankAccount2;

    @Column(name = "bank_id_account3", length = 10)
    private String bankIdAccount3;

    @Column(name = "bank_account3", length = 50)
    private String bankAccount3;

    public Seller() {
    }

    public Seller(Integer sellerId, Integer sellerUserId, String sellerName, String sellerImage, String sellerImagePublicId, String sellerIntroduce, Date joinTime, Date lastLoginTime, Integer sellerViolationCount, String bankIdAccount1, String bankAccount1, String bankIdAccount2, String bankAccount2, String bankIdAccount3, String bankAccount3) {
        this.sellerId = sellerId;
        this.sellerUserId = sellerUserId;
        this.sellerName = sellerName;
        this.sellerImage = sellerImage;
        this.sellerImagePublicId = sellerImagePublicId;
        this.sellerIntroduce = sellerIntroduce;
        this.joinTime = joinTime;
        this.lastLoginTime = lastLoginTime;
        this.sellerViolationCount = sellerViolationCount;
        this.bankIdAccount1 = bankIdAccount1;
        this.bankAccount1 = bankAccount1;
        this.bankIdAccount2 = bankIdAccount2;
        this.bankAccount2 = bankAccount2;
        this.bankIdAccount3 = bankIdAccount3;
        this.bankAccount3 = bankAccount3;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(Integer sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerImage() {
        return sellerImage;
    }

    public void setSellerImage(String sellerImage) {
        this.sellerImage = sellerImage;
    }

    public String getSellerImagePublicId() {
        return sellerImagePublicId;
    }

    public void setSellerImagePublicId(String sellerImagePublicId) {
        this.sellerImagePublicId = sellerImagePublicId;
    }

    public String getSellerIntroduce() {
        return sellerIntroduce;
    }

    public void setSellerIntroduce(String sellerIntroduce) {
        this.sellerIntroduce = sellerIntroduce;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getSellerViolationCount() {
        return sellerViolationCount;
    }

    public void setSellerViolationCount(Integer sellerViolationCount) {
        this.sellerViolationCount = sellerViolationCount;
    }

    public String getBankIdAccount1() {
        return bankIdAccount1;
    }

    public void setBankIdAccount1(String bankIdAccount1) {
        this.bankIdAccount1 = bankIdAccount1;
    }

    public String getBankAccount1() {
        return bankAccount1;
    }

    public void setBankAccount1(String bankAccount1) {
        this.bankAccount1 = bankAccount1;
    }

    public String getBankIdAccount2() {
        return bankIdAccount2;
    }

    public void setBankIdAccount2(String bankIdAccount2) {
        this.bankIdAccount2 = bankIdAccount2;
    }

    public String getBankAccount2() {
        return bankAccount2;
    }

    public void setBankAccount2(String bankAccount2) {
        this.bankAccount2 = bankAccount2;
    }

    public String getBankIdAccount3() {
        return bankIdAccount3;
    }

    public void setBankIdAccount3(String bankIdAccount3) {
        this.bankIdAccount3 = bankIdAccount3;
    }

    public String getBankAccount3() {
        return bankAccount3;
    }

    public void setBankAccount3(String bankAccount3) {
        this.bankAccount3 = bankAccount3;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Seller{");

        sb.append(", user=").append(sellerUserId);
        sb.append(", sellerName=").append(sellerName);
        sb.append(", sellerImage='").append(sellerImage).append('\'');
        sb.append(", sellerImagePublicId='").append(sellerImagePublicId).append('\'');
        sb.append(", sellerIntroduce='").append(sellerIntroduce).append('\'');
        sb.append(", joinTime=").append(joinTime);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", sellerViolationCount=").append(sellerViolationCount);
        sb.append(", bankIdAccount1='").append(bankIdAccount1).append('\'');
        sb.append(", bankAccount1='").append(bankAccount1).append('\'');
        sb.append(", bankIdAccount2='").append(bankIdAccount2).append('\'');
        sb.append(", bankAccount2='").append(bankAccount2).append('\'');
        sb.append(", bankIdAccount3='").append(bankIdAccount3).append('\'');
        sb.append(", bankAccount3='").append(bankAccount3).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @OneToMany(mappedBy = "seller",
            fetch = FetchType.LAZY ,
            cascade = {CascadeType.ALL})
    private List<Product> product;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public void addProduct(Product tmpProduct){
        if(product==null){
            product = new ArrayList<>();
        }
        product.add(tmpProduct);

        tmpProduct.setSeller(this);
    }

}
