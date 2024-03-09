package com.ispan.projectX.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "product_gallery_cloud")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "product")
public class ProductGalleryCloud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Integer imgId;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @Column(name = "img_path", nullable = false)
    private String imgPath;

    @Column(name = "img_path_public_id")
    private String imgPathPublicId;

    @Column(name = "img_description")
    private String imgDescription;

    public ProductGalleryCloud() {
    }
    ///////////////////////////////

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgPathPublicId() {
        return imgPathPublicId;
    }

    public void setImgPathPublicId(String imgPathPublicId) {
        this.imgPathPublicId = imgPathPublicId;
    }

    public String getImgDescription() {
        return imgDescription;
    }

    public void setImgDescription(String imgDescription) {
        this.imgDescription = imgDescription;
    }


    ///////////////////////////////



    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ProductGalleryCloudRepository{");
        sb.append("imgId=").append(imgId);
        sb.append(", product=").append(product);
        sb.append(", imgPath='").append(imgPath).append('\'');
        sb.append(", imgPathPublicId='").append(imgPathPublicId).append('\'');
        sb.append(", imgDescription='").append(imgDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
