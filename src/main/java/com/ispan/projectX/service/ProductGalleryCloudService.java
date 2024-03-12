package com.ispan.projectX.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ispan.projectX.dao.product.ProductGalleryCloudRepository;
import com.ispan.projectX.dao.product.ProductRepository;
import com.ispan.projectX.entity.product.Product;
import com.ispan.projectX.entity.product.ProductGalleryCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Service
public class ProductGalleryCloudService  {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ProductGalleryCloudRepository productGalleryCloudRepository;
    @Autowired
    private ProductRepository productRepository;


    public ProductGalleryCloud findByImgId(Integer imgId){
        return productGalleryCloudRepository.findByImgId(imgId);
    }


    public ProductGalleryCloud productUploadCloud(MultipartFile file, Integer productId,String imgDescription){
        try {
            //上傳到producutFolder裡
            Map data = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "productFolder"));
            //也可以用ObjectUtils.asMap( "folder", "your_folder_name")

            Product product = productRepository.findByProductId(productId);

            ProductGalleryCloud cloud = new ProductGalleryCloud();
            cloud.setProduct(product);
            cloud.setImgPath((String) data.get("url"));
            cloud.setImgPathPublicId((String) data.get("public_id"));
            cloud.setImgDescription(imgDescription);

            System.out.println(product.getProductName()+"的照片上傳成功!");

            //更新時間
            product.setModifiedDate(new Date());
            productRepository.save(product);
            return productGalleryCloudRepository.save(cloud);

        } catch (IOException e) {
            throw new RuntimeException("Image uploading fail !!");
        }

    }

    public ProductGalleryCloud updateImgCloud(MultipartFile file, Integer productId,ProductGalleryCloud cloud,String imgDescription)  {
        //先刪除原本在cloudinary中對應的圖片
        String publicId = cloud.getImgPathPublicId();
        if(publicId!=null) {
            Map delResult = null;
            try {
                delResult = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
                System.out.println("Deleted image from Cloudinary: " + delResult);

                //上傳新的圖片到producutFolder裡
                Map data = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "productFolder"));
                System.out.println("Upload new image succeed!");

                //更新資料庫中對應的資料
                cloud.setImgPath((String) data.get("url"));
                cloud.setImgPathPublicId((String) data.get("public_id"));
                cloud.setImgDescription(imgDescription);
                productGalleryCloudRepository.save(cloud);
                System.out.println("update db info succeed!");
                //更新時間
                Product product = productRepository.findByProductId(productId);
                product.setModifiedDate(new Date());
                productRepository.save(product);
                System.out.println("update db time succeed!");

                return cloud;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println("本來就沒有圖片");
        return null;
    }

}
