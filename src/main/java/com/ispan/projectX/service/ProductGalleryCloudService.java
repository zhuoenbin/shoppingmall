package com.ispan.projectX.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ispan.projectX.dao.product.ProductGalleryCloudRepository;
import com.ispan.projectX.entity.product.Product;
import com.ispan.projectX.entity.product.ProductGalleryCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ProductGalleryCloudService  {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ProductGalleryCloudRepository productGalleryCloudRepository;



    public ProductGalleryCloud productUploadCloud(MultipartFile file, Product product,String imgDescription){
        try {
            //上傳到producutFolder裡
            Map data = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "productFolder"));
            //也可以用ObjectUtils.asMap( "folder", "your_folder_name")

            ProductGalleryCloud cloud = new ProductGalleryCloud();
            cloud.setProduct(product);
            cloud.setImgPath((String) data.get("url"));
            cloud.setImgPathPublicId((String) data.get("public_id"));
            cloud.setImgDescription(imgDescription);

            System.out.println(product.getProductName()+"的照片上傳成功!");
            return productGalleryCloudRepository.save(cloud);

        } catch (IOException e) {
            throw new RuntimeException("Image uploading fail !!");
        }

    }
}
