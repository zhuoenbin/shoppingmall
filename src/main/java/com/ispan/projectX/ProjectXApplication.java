package com.ispan.projectX;

import com.ispan.projectX.dao.*;
import com.ispan.projectX.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ProjectXApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectXApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(UsersRepository usersRepository,
											   SellerRepository sellerRepository,
											   ProductRepository productRepository,
											   ShoppingCartRepository shoppingCartRepository,
											   ProductGalleryRepository productGalleryRepository,
											   CreditCardRepository creditCardRepository,
											   EmployeeRepository employeeRepository) {
		return runner -> {

//			List<Users> lis = usersRepository.findAll();
//			for (Users li : lis) {
//				System.out.println(li.toString());
//			}

//			// 登入後 自動找 user 的購物車
//			Users user = usersRepository.findByUserId(1);
//
//			List<ShoppingCart> lis = shoppingCartRepository.findByUsers(user);
//			for (ShoppingCart li : lis) {
//				System.out.println(li);
//			}

////			商品主頁
//			or (Product li : lis) {
//				System.out.println(li.toString());
//			}

//			List<ProductGallery> productGallery =  productGalleryRepository.findAll();
//			for (ProductGallery li : productGallery) {
//				System.out.println(li.toString());
//			}

//			Product product = productRepository.findByProductId(21);
//			List<ProductGallery> productGallery = productGalleryRepository.findByProduct(product);
//
//			for (ProductGallery li : productGallery) {
//				System.out.println(li.toString());
//				System.out.println(li.getImgPath());
//			}



		};
	}
}

