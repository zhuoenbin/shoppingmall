package com.ispan.projectX;

import com.ispan.projectX.dao.*;
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
	public CommandLineRunner commandLineRunner(ComplaintRepository complaintRepository,
											   EmployeeRepository employeeRepository,
											   UsersRepository usersRepository,
											   SellerRepository sellerRepository,
											   PushReceiverGroupRepository pushReceiverGroupRepository) {
		return runner -> {
//			Seller sel = sellerRepository.findBySellerId(2);
//
//			PushReceiverGroup group2 = new PushReceiverGroup();
//			group2.setGroupName("Group B");
//			group2.setSeller(sel);
//			group2.setGroupBuildTime(new Date());
//			group2.setGroupUpdateTime(new Date());
//
//			pushReceiverGroupRepository.save(group2);




        };
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




