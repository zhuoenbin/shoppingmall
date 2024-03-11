package com.ispan.projectX.entity.product;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ispan.projectX.entity.Employee;
import com.ispan.projectX.entity.Seller;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;

@Entity
@Table(name="product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productId")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @NotBlank
    @Column(name = "product_name", nullable = false)
    private String productName;



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    private Seller seller;



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @Column(name = "unit_price")
    private Integer unitPrice;



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private ProductCategory category;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "reserved_quantity")
    private Integer reservedQuantity;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")//在JAVA環境內的時間格式(輸入時調整，輸出為另一種)，EE為星期幾
    @Column(name = "listing_date")
    private Date listingDate;


    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")//在JAVA環境內的時間格式(輸入時調整，輸出為另一種)，EE為星期幾
    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "product_status", length = 20)
    private String productStatus;

    @Column(name = "product_status_des", length = 200)
    private String productStatusDescription;

    @Column(name = "promotion_id")
    private Integer promotionId;

    @Column(name = "official_discount_id")
    private Integer officialDiscountId;

    @Column(name = "seller_discount_id")
    private Integer sellerDiscountId;

    /////////////////////////////////////////

    @PrePersist //在物件轉換到persistent狀態以前，做這個function
    public void onCreate() {
        if(listingDate==null && modifiedDate==null) {
            listingDate=new Date();
            modifiedDate=new Date();
        }
    }

    /////////////////////////////////////////

    @OneToMany(mappedBy = "product",
            fetch = FetchType.LAZY ,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProductGallery> productGalleries;

    @OneToMany(mappedBy = "product",
            fetch = FetchType.LAZY ,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProductGalleryCloud>productGalleryClouds;

    ///////////////////////////////////
    public Product() {
    }

    public Product(Integer productId, String productName, Seller seller,
                   Integer unitPrice, ProductCategory category, Integer stock,
                   Integer reservedQuantity, Date listingDate,
                   Date modifiedDate, String description) {
        this.productId = productId;
        this.productName = productName;
        this.seller = seller;
        this.unitPrice = unitPrice;
        this.category = category;
        this.stock = stock;
        this.reservedQuantity = reservedQuantity;
        this.listingDate = listingDate;
        this.modifiedDate = modifiedDate;
        this.description = description;
    }

    ///////////////////////////////////

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

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
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

    public String getProductStatusDescription() {
        return productStatusDescription;
    }

    public void setProductStatusDescription(String productStatusDescription) {
        this.productStatusDescription = productStatusDescription;
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

    public List<ProductGallery> getProductGalleries() {
        return productGalleries;
    }

    public void setProductGalleries(List<ProductGallery> productGalleries) {
        this.productGalleries = productGalleries;
    }

    public List<ProductGalleryCloud> getProductGalleryClouds() {
        return productGalleryClouds;
    }

    public void setProductGalleryClouds(List<ProductGalleryCloud> productGalleryClouds) {
        this.productGalleryClouds = productGalleryClouds;
    }


    //////////////////////////////////

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("productId=").append(productId);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", sellerId=").append(seller);
        sb.append(", employeeId=").append(employee);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", categoryId=").append(category);
        sb.append(", stock=").append(stock);
        sb.append(", reservedQuantity=").append(reservedQuantity);
        sb.append(", listingDate=").append(listingDate);
        sb.append(", modifiedDate=").append(modifiedDate);
        sb.append(", description='").append(description).append('\'');
        sb.append(", productStatus='").append(productStatus).append('\'');
        sb.append(", promotionId=").append(promotionId);
        sb.append(", officialDiscountId=").append(officialDiscountId);
        sb.append(", sellerDiscountId=").append(sellerDiscountId);

        sb.append('}');
        return sb.toString();
    }
}
