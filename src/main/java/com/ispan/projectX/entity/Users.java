package com.ispan.projectX.entity;


import com.ispan.projectX.entity.order.OrderTable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_gender", length = 10)
    private String userGender;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "buyer_violation_count")
    private Integer buyerViolationCount;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "seller_qualified")
    private Integer sellerQualified;

    @Column(name = "user_status", length = 10)
    private String userStatus;

    @Column(name = "bank_id_account1", length = 10)
    private String bankIdAccount1;

    @Column(name = "bank_account1", length = 50)
    private String bankAccount1;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY ,
            cascade = {CascadeType.ALL})
    private List<OrderTable> orderTables;




    public List<OrderTable> getOrderTables() {
        return orderTables;
    }

    public void setOrderTables(List<OrderTable> orderTables) {
        this.orderTables = orderTables;
    }

    public void add(OrderTable orderTable){
        if(orderTables == null){
            orderTables = new ArrayList<>();
        }
        orderTables.add(orderTable);
        orderTable.setUser(this);
    }

    public Users() {
    }

    public Users(String lastName, String firstName, String userEmail, String userPassword, String userGender, Date birthDate, Integer buyerViolationCount, Date lastLoginTime, Integer sellerQualified, String userStatus, String bankIdAccount1, String bankAccount1) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userGender = userGender;
        this.birthDate = birthDate;
        this.buyerViolationCount = buyerViolationCount;
        this.lastLoginTime = lastLoginTime;
        this.sellerQualified = sellerQualified;
        this.userStatus = userStatus;
        this.bankIdAccount1 = bankIdAccount1;
        this.bankAccount1 = bankAccount1;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getBuyerViolationCount() {
        return buyerViolationCount;
    }

    public void setBuyerViolationCount(Integer buyerViolationCount) {
        this.buyerViolationCount = buyerViolationCount;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getSellerQualified() {
        return sellerQualified;
    }

    public void setSellerQualified(Integer sellerQualified) {
        this.sellerQualified = sellerQualified;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
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



    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("userId=").append(userId);
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", userEmail='").append(userEmail).append('\'');
        sb.append(", userPassword='").append(userPassword).append('\'');
        sb.append(", userGender='").append(userGender).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", buyerViolationCount=").append(buyerViolationCount);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", sellerQualified=").append(sellerQualified);
        sb.append(", userStatus='").append(userStatus).append('\'');
        sb.append(", bankIdAccount1='").append(bankIdAccount1).append('\'');
        sb.append(", bankAccount1='").append(bankAccount1).append('\'');
        sb.append('}');
        return sb.toString();
    }
}