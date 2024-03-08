package com.ispan.projectX.entity.product;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "unit_price")
    private Integer unitPrice;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "reserved_quantity")
    private Integer reservedQuantity;

    @Column(name = "listing_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date listingDate;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "product_status", length = 20)
    private String productStatus;

    @Column(name = "product_status_des", length = 200)
    private String productStatusDes;

    @Column(name = "promotion_id")
    private Integer promotionId;

    @Column(name = "official_discount_id")
    private Integer officialDiscountId;

    @Column(name = "seller_discount_id")
    private Integer sellerDiscountId;

    public Product() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(Integer reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public Date getListingDate() {
        return listingDate;
    }

    public void setListingDate(Date listingDate) {
        this.listingDate = listingDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductStatusDes() {
        return productStatusDes;
    }

    public void setProductStatusDes(String productStatusDes) {
        this.productStatusDes = productStatusDes;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getOfficialDiscountId() {
        return officialDiscountId;
    }

    public void setOfficialDiscountId(Integer officialDiscountId) {
        this.officialDiscountId = officialDiscountId;
    }

    public Integer getSellerDiscountId() {
        return sellerDiscountId;
    }

    public void setSellerDiscountId(Integer sellerDiscountId) {
        this.sellerDiscountId = sellerDiscountId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("productId=").append(productId);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", sellerId=").append(sellerId);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", stock=").append(stock);
        sb.append(", reservedQuantity=").append(reservedQuantity);
        sb.append(", listingDate=").append(listingDate);
        sb.append(", modifiedDate=").append(modifiedDate);
        sb.append(", description='").append(description).append('\'');
        sb.append(", productStatus='").append(productStatus).append('\'');
        sb.append(", productStatusDes='").append(productStatusDes).append('\'');
        sb.append(", promotionId=").append(promotionId);
        sb.append(", officialDiscountId=").append(officialDiscountId);
        sb.append(", sellerDiscountId=").append(sellerDiscountId);
        sb.append('}');
        return sb.toString();
    }
}
