package com.ispan.projectX.controller;

import com.ispan.projectX.dao.SellerRepository;
import com.ispan.projectX.dto.product.CreateProductDto;
import com.ispan.projectX.dto.product.UpdateProductDto;
import com.ispan.projectX.entity.Seller;
import com.ispan.projectX.entity.product.Product;
import com.ispan.projectX.entity.product.ProductCategory;
import com.ispan.projectX.entity.product.ProductGalleryCloud;
import com.ispan.projectX.service.ProductCategoryService;
import com.ispan.projectX.service.ProductGalleryCloudService;
import com.ispan.projectX.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
public class ProductController {
    @Autowired //暫時引用
    private SellerRepository sellerRepository;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductGalleryCloudService productGalleryCloudService;

    //-----------------ProductCategory-----------------

    //得到所有類別
    @GetMapping("/category")
    public List<ProductCategory> getAllCategories(){
        return productCategoryService.listAllCategories();
    }

    //新增類別
    @PostMapping("/category/create")
    public  ProductCategory insertNewCategory(@RequestParam String categoryName){
        return productCategoryService.createCategory(categoryName);
    }

    //更新類別
    @PutMapping("/category/update")
    public ProductCategory updateCategory(@RequestBody ProductCategory category){
        return productCategoryService.updateCategory(category);
    }

    //-----------------Product & Cloudinary-----------------

    //依id搜尋指定商品
    @GetMapping("/product/productId")
    public Product getProductById(@RequestParam Integer productId){
        return productService.findByProductId(productId);
    }


    //依productName搜尋商品
    @GetMapping("/product/productName")
    public List<Product> getProductsByProductName(@RequestParam String productName){
        List<Product> result = productService.findByProductName(productName);
        System.out.println("依此商品名找到: "+result.size()+" 筆商品");//檢查數量
        return result;
    }

    //依賣場名搜尋商品
    @GetMapping("/product/mall")
    public List<Product> getBySellerName(@RequestParam("mall") String sellerName){
        List<Product> result = productService.findBySellerName(sellerName);
        System.out.println("此商城有: "+result.size()+" 筆商品");//檢查數量
        return result;
    }

    //依類別搜尋商品
    @GetMapping("/product/category")
    public List<Product> getByCategoryName(@RequestParam("category") String categoryName){
        List<Product> result = productService.findByCategoryName(categoryName);
        System.out.println("此類別有: "+result.size()+" 筆商品");//檢查數量
        return result;

    }

    //在商城中依類別尋找商品
    @GetMapping("/product/{mall}/category")
    public List<Product> findBySellerAndCategory(@PathVariable("mall") String sellerName,
                                                 @RequestParam("category") String categoryName){
        List<Product> result = productService.findBySellerAndCategory(sellerName, categoryName);
        System.out.println("在此商城中 "+categoryName+" 有: "+result.size()+" 筆商品");//檢查數量
        return result;
    }




    //列出所有商品

    @GetMapping("/product")
    public List<Product> listAllProduct(){
        List<Product> allProduct = productService.listAllProduct();
        //測試撈img網址
        for(Product product: allProduct) {
            List<ProductGalleryCloud> clouds = product.getProductGalleryClouds();
            if (!clouds.isEmpty()) {
//                for(ProductGalleryCloud cloudImg : clouds){//因為每一個產品可以多張圖片
//                    System.out.println(cloudImg.getImgPath());//撈出每一個圖片的網址
//                }
                System.out.println("productId: "+product.getProductId()+": "+clouds.get(0).getImgPath());//只撈出每一個產品第一個圖片網址
            } else {
                System.out.println("productId: "+product.getProductId() + " 此商品尚未有圖片");
            }
        }

        return allProduct;
    }

    //新增商品
    @PostMapping("/product/insert")
    public Product createProduct (@RequestBody CreateProductDto createProductDto){
        return productService.createProduct(createProductDto);
    }

    //更新商品(賣家更新商品名,單價,庫存,商品敘述)
    @PutMapping("/product/update")
    public Product updateProduct (@RequestBody UpdateProductDto updateProductDto){
        return productService.updateProduct(updateProductDto);
    }

    //插入商品圖片(可多張)
    @PostMapping("/product/image/insert")
    public List<ProductGalleryCloud> insertProductImg(@RequestParam("productImg")MultipartFile[] images,
                                                @RequestParam Integer productId, @RequestParam String imgDescription){
        List<ProductGalleryCloud> uploadedImages = new ArrayList<>();
        for(MultipartFile image:images){
            ProductGalleryCloud cloud = productGalleryCloudService.productUploadCloud(image, productId, imgDescription);
            uploadedImages.add(cloud);
        }
        return uploadedImages;
    }

    //更新商品圖片與敘述(單張)
    @PutMapping("/product/image/update")
    public ProductGalleryCloud updateProductImg(@RequestParam("productImg")MultipartFile file,
                                                @RequestParam Integer productId,
                                                @RequestParam Integer imgId,
                                                @RequestParam String imgDescription) {
        ProductGalleryCloud cloud = productGalleryCloudService.findByImgId(imgId);

        return productGalleryCloudService.updateImgCloud(file,productId,cloud,imgDescription);
    }

    @GetMapping("/product/insertWithImg")
    public String uploadPhoto() {
        return "uploadProductPage";
    }

    //同時新增商品及上傳圖片 uploadProductPage.html

    @PostMapping("/product/insertWithImg")
    public String createProductWithCloudImg (@RequestParam String productName,
                                              @RequestParam Integer sellerId,
                                              @RequestParam Integer unitPrice,
                                              @RequestParam Integer categoryId,
                                              @RequestParam Integer stock,
                                              @RequestParam Integer reservedQuantity,
                                              @RequestParam String description,
                                              @RequestParam MultipartFile[] productImg,
                                              @RequestParam String imgDescription
    ){

        Seller seller = sellerRepository.findBySellerId(sellerId);
        ProductCategory category = productCategoryService.findByCategoryId(categoryId);

        Product product = new Product();

        product.setProductName(productName);
        product.setSeller(seller);
        product.setUnitPrice(unitPrice);
        product.setCategory(category);
        product.setStock(stock);
        product.setReservedQuantity(reservedQuantity);
        product.setDescription(description);
        Product inserted = productService.createProduct(product);//創建後的商品物件
        Integer insertedProductId = inserted.getProductId();

        List<ProductGalleryCloud> uploadedImages = new ArrayList<>();
        for(MultipartFile image:productImg){
            ProductGalleryCloud cloud = productGalleryCloudService.productUploadCloud(image, insertedProductId, imgDescription);
            uploadedImages.add(cloud);
        }

        return "uploadProductPage";
    }
//    public Product createProductWithCloudImg (@RequestBody CreateProductDto createProductDto,
//                                              @RequestPart("productImg")MultipartFile[] images,
//                                              @RequestPart String imgDescription){
//
//        Product inserted = productService.createProduct(createProductDto);//創建後的商品物件
//        Integer insertedProductId = inserted.getProductId();
//
//        //將圖片插入創建好的商品
//        List<ProductGalleryCloud> uploadedImages = new ArrayList<>();
//        for(MultipartFile image:images){
//            ProductGalleryCloud cloud = productGalleryCloudService.productUploadCloud(image, insertedProductId, imgDescription);
//            uploadedImages.add(cloud);
//        }
//        return productService.findByProductId(insertedProductId);
//    }



}
