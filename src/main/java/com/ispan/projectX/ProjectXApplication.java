package com.ispan.projectX;

import com.ispan.projectX.dao.*;
import com.ispan.projectX.dao.order.OrderDetailRepository;
import com.ispan.projectX.dao.order.OrderTableRepository;
import com.ispan.projectX.dao.product.ProductRepository;
import com.ispan.projectX.entity.Seller;
import com.ispan.projectX.entity.Users;
import com.ispan.projectX.entity.order.OrderTable;
import com.ispan.projectX.entity.order.OrderDetail;
import com.ispan.projectX.entity.product.Product;
import com.ispan.projectX.service.AccountServiceImpl;
import com.ispan.projectX.service.MailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ProjectXApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectXApplication.class, args);
	}




	@Bean
	public CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository,
											   UsersRepository usersRepository,
											   SellerRepository sellerRepository,
											   OrderTableRepository orderTableRepository,
											   ProductRepository productRepository,
											   OrderDetailRepository orderDetailRepository,
											   AccountServiceImpl accountService) {
		return runner -> {
//			accountService.sendCodeForResetPassword("a50064xxx@gmail.com");

			//訂單產生器
//			generateOrderDetailWithProduct(orderDetailRepository,
//					productRepository,
//					usersRepository,
//					sellerRepository,
//					orderTableRepository);
		};
	}


	private void generateOrderDetailWithProduct(OrderDetailRepository orderDetailRepository,
											   ProductRepository productRepository,
												UsersRepository usersRepository,
												SellerRepository sellerRepository,
												OrderTableRepository orderTableRepository) {
		Product or1 = productRepository.findByProductId(1);
		Product or2 = productRepository.findByProductId(2);

		Users user = usersRepository.findByUserId(1);
		Seller seller = sellerRepository.findBySellerId(2);

		OrderTable order1 = new OrderTable(
				null,           // 使用者
				null,         // 賣家
				LocalDateTime.now(),  // 訂單日期
				1500,            // 總價格
				100,             // 賣家折扣價格
				50,              // 賣家優惠券價格
				80,              // 官方折扣價格
				30,              // 官方優惠券價格
				"特價活動",      // 折扣描述
				"特價券",        // 優惠券描述
				1,               // 付款方式
				1,               // 付款狀態
				null,            // 賣家確認日期
				null,            // 賣家發貨日期
				null,            // 物流發貨日期
				null,            // 物流抵達日期
				null,            // 買家收貨日期
				null,            // 買家確認日期
				null,            // 訂單取消日期
				"宅配速達",      // 運送公司名稱
				"台北市",        // 城市
				"信義區",        // 行政區
				"忠孝東路一段100號", // 住址
				80               // 運費
		);
		OrderTable order = orderTableRepository.save(order1);

		order.setSeller(seller);
		order.setUser(user);
		orderTableRepository.save(order);

		OrderDetail od1 = orderDetailRepository.save(new OrderDetail(null, null, 2, 100, 0));
		OrderDetail od2 = orderDetailRepository.save(new OrderDetail(null, null, 1, 50, 5));

		od1.setProduct(or1);
		od1.setOrder(order);
		od2.setProduct(or2);
		od2.setOrder(order);

		orderDetailRepository.save(od1);
		orderDetailRepository.save(od2);

	}





//	//新增seller，這資料表設計有點怪，必須抓user.id進去seller.id
//	private void addSeller(SellerRepository sellerRepository,
//						   UsersRepository usersRepository){
//
//		Users user = usersRepository.findByUserId(2);
//
//		Seller seller = new Seller();
//		seller.setSellerId(user.getUserId());
//		seller.setSellerName(123);
//		seller.setSellerImage("seller_image_url_1");
//		seller.setSellerImagePublicId("public_id_1");
//		seller.setSellerIntroduce("Introduce for Seller 1");
//		seller.setJoinTime(new Date());
//		seller.setLastLoginTime(new Date());
//		seller.setSellerViolationCount(0);
//		seller.setBankIdAccount1("bank_id_1");
//		seller.setBankAccount1("bank_account_1");
//		seller.setBankIdAccount2("bank_id_2");
//		seller.setBankAccount2("bank_account_2");
//		seller.setBankIdAccount3("bank_id_3");
//		seller.setBankAccount3("bank_account_3");
//		sellerRepository.save(seller);
//	}
//	//使用Seller	Repository 帶入參數SellerId，反向找出Users
//	private void findUserBySellerId(SellerRepository sellerRepository){
//		Seller seller = sellerRepository.findBySellerId(2);
//		Users user = seller.getUser();
//		System.out.println(user.toString());
//	}
//
//	//找出該employee所處理的所有Complaint(By Employee物件)，使用employeeRepository
//	private void findComplaintByEmployee(EmployeeRepository employeeRepository){
//		Employee emp = employeeRepository.findByEmployeeId(2);
//		List<Complaint> lis = employeeRepository.findComplaintByEmployee(emp);
//		for (Complaint li : lis) {
//			System.out.println(li.toString());
//		}
//	}
//
//	//找出該employee所處理的所有Complaint(By EmployeeId)，使用complaintRepository
//	private  void findComplaintByEmployeeId(ComplaintRepository complaintRepository){
//		List<Complaint> lis = complaintRepository.findByEmployeeEmployeeId(2);
//		for(Complaint li:lis){
//			System.out.println(li.toString());
//		}
//	}
//
//	//在Complaint加入EmployeeId(讓他們有關係)
//	private void setEmployeeIdInComplaint(ComplaintRepository complaintRepository,EmployeeRepository employeeRepository){
//		Employee emp = employeeRepository.findByEmployeeId(1);
//		Complaint com = complaintRepository.findByComplaintId(1);
//		com.setEmployee(emp);
//		complaintRepository.save(com);
//	}
//	private void findAllEmployee(EmployeeRepository employeeRepository) {
//		List<Employee> lis = employeeRepository.findAll();
//		for(Employee li : lis){
//			System.out.println(li.toString());
//		}
//	}

}




