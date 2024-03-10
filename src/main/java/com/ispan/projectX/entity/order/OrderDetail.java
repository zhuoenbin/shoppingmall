package com.ispan.projectX.entity.order;

import com.ispan.projectX.entity.product.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private OrderTable order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    @Column(name = "discount")
    private Integer discount;

    public OrderDetail() {
    }

    public OrderDetail(Long orderDetailId, OrderTable order, Product product, Integer quantity, Integer price, Integer discount) {
        this.orderDetailId = orderDetailId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public OrderTable getOrder() {
        return order;
    }

    public void setOrder(OrderTable order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderDetail{");
        sb.append("orderDetailId=").append(orderDetailId);
        sb.append(", order=").append(order);
        sb.append(", product=").append(product);
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append(", discount=").append(discount);
        sb.append('}');
        return sb.toString();
    }

}
