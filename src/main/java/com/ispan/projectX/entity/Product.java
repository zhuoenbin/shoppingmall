package com.ispan.projectX.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId;

    @Column(name="product_name", nullable = false)
    private String productName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="employee_id")
    private Employee employeeId;

    @Column(name="unit_price")
    private Integer unitPrice;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name="stock")
    private Integer stock;

    @Column(name="reserved_quantity")
    private Integer reservedQuantity;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column(name="listing_date")
    private Date listingDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column(name="modified_date")
    private Date modifiedDate;

    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @Column(name="product_status", length = 20)
    private String productStatus;

    @Column(name="product_status_des", length = 200)
    private String productStatusDes;

    @Column(name="promotion_id")
    private Integer promotionId;

    @Column(name="official_discount_id")
    private Integer officialActivityDiscount;

    @Column(name="seller_discount_id")
    private Integer sellerActivityDiscount;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id", insertable = false, updatable = false)
    private Seller seller;

    public Product() {
    }

    public Product(Integer productId, String productName, Employee employeeId, Integer unitPrice, Integer categoryId, Integer stock, Integer reservedQuantity, Date listingDate, Date modifiedDate, String description, String productStatus, String productStatusDes, Integer promotionId, Integer officialActivityDiscount, Integer sellerActivityDiscount, Seller seller) {
        this.productId = productId;
        this.productName = productName;
        this.employeeId = employeeId;
        this.unitPrice = unitPrice;
        this.categoryId = categoryId;
        this.stock = stock;
        this.reservedQuantity = reservedQuantity;
        this.listingDate = listingDate;
        this.modifiedDate = modifiedDate;
        this.description = description;
        this.productStatus = productStatus;
        this.productStatusDes = productStatusDes;
        this.promotionId = promotionId;
        this.officialActivityDiscount = officialActivityDiscount;
        this.sellerActivityDiscount = sellerActivityDiscount;
        this.seller = seller;
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

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
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

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getOfficialActivityDiscount() {
        return officialActivityDiscount;
    }

    public void setOfficialActivityDiscount(Integer officialActivityDiscount) {
        this.officialActivityDiscount = officialActivityDiscount;
    }

    public Integer getSellerActivityDiscount() {
        return sellerActivityDiscount;
    }

    public void setSellerActivityDiscount(Integer sellerActivityDiscount) {
        this.sellerActivityDiscount = sellerActivityDiscount;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getProductStatusDes() {
        return productStatusDes;
    }

    public void setProductStatusDes(String productStatusDes) {
        this.productStatusDes = productStatusDes;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("productId=").append(productId);
        sb.append(", productName='").append(productName).append('\'');
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
        sb.append(", officialActivityDiscount=").append(officialActivityDiscount);
        sb.append(", sellerActivityDiscount=").append(sellerActivityDiscount);
        sb.append(", seller=").append(seller);
        sb.append('}');
        return sb.toString();
    }

    @OneToMany(mappedBy = "product",
            fetch = FetchType.LAZY ,
            cascade = {CascadeType.ALL})
    private List<ShoppingCart> shoppingCart;

    public List<ShoppingCart> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<ShoppingCart> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void add(ShoppingCart tmpShoppingCart){
        if(shoppingCart==null){
            shoppingCart = new ArrayList<>();
        }
        shoppingCart.add(tmpShoppingCart);

        tmpShoppingCart.setProduct(this);
    }

    @OneToMany(mappedBy = "product",
            fetch = FetchType.LAZY ,
            cascade = {CascadeType.ALL})
    private List<ProductGallery> productGallery;

    public List<ProductGallery> getProductGallery() {
        return productGallery;
    }

    public void setProductGallery(List<ProductGallery> productGallery) {
        this.productGallery = productGallery;
    }

    public void add(ProductGallery tmpProductGallery){
        if(productGallery==null){
            productGallery = new ArrayList<>();
        }
        productGallery.add(tmpProductGallery);

        tmpProductGallery.setProduct(this);
    }

}
