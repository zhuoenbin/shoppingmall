package com.ispan.projectX.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_gallery_cloud")
public class ProductGalleryCloud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long imgId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "img_path", nullable = false)
    private String imgPath;

    @Column(name = "img_path_public_id")
    private String imgPathPublicId;

    @Column(name = "img_description")
    private String imgDescription;

    public ProductGalleryCloud() {
    }

    public ProductGalleryCloud(Long imgId, Product product, String imgPath, String imgPathPublicId, String imgDescription) {
        this.imgId = imgId;
        this.product = product;
        this.imgPath = imgPath;
        this.imgPathPublicId = imgPathPublicId;
        this.imgDescription = imgDescription;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ProductGalleryCloud{");
        sb.append("imgId=").append(imgId);
        sb.append(", product=").append(product);
        sb.append(", imgPath='").append(imgPath).append('\'');
        sb.append(", imgPathPublicId='").append(imgPathPublicId).append('\'');
        sb.append(", imgDescription='").append(imgDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
