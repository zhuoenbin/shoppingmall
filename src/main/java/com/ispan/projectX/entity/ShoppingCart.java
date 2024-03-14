package com.ispan.projectX.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

    @Entity
    @Table(name = "shopping_cart")
    public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private Integer shoppingCartId;

    // 多對一 user table
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private Users users;

    // 多對一 product table
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private Integer unitPrice;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss EE")
    @Column(name = "created_time")
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss EE")
    @Column(name = "updated_time")
    private Date updatedTime;

    public ShoppingCart() {
    }

    public ShoppingCart(Users users, Product product, Integer quantity, Integer unitPrice, Date createdTime, Date updatedTime) {
        this.shoppingCartId = shoppingCartId;
        this.users = users;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("ShoppingCart{");
            sb.append("shoppingCartId=").append(shoppingCartId);
            sb.append(", users=").append(users);
            sb.append(", product=").append(product);
            sb.append(", quantity=").append(quantity);
            sb.append(", unitPrice=").append(unitPrice);
            sb.append(", createdTime=").append(createdTime);
            sb.append(", updatedTime=").append(updatedTime);
            sb.append('}');
            return sb.toString();
        }

        public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
