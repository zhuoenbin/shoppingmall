package com.ispan.projectX.service;

import com.ispan.projectX.dao.EmployeeRepository;
import com.ispan.projectX.dao.UsersRepository;
import com.ispan.projectX.dto.Passport;
import com.ispan.projectX.entity.Employee;
import com.ispan.projectX.entity.Users;
import com.ispan.projectX.service.interfacefile.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {

    private UsersRepository usersRepository;
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;



    @Autowired
    public AccountServiceImpl(UsersRepository usersRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean registerEmailCheak(String email) {
        // 檢查資料庫中是否已經存在相同的 email
        Users tmpUser = usersRepository.findUsersByUserEmail(email);
        return tmpUser == null? true : false;
    }
    @Override
    public void register(Users user){
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setLastLoginTime(new Date());
        usersRepository.save(user);
    };


    @Override
    public Passport getPassportFromFormLogin(String username) {
        Users user = usersRepository.findUsersByUserEmail(username);
        Passport passportDTO = null;
        if(user != null){
            user.setLastLoginTime(new Date());
            usersRepository.save(user);
            passportDTO = new Passport(user.getLastName(),user.getUserEmail(), user.getUserId(), null);
            return passportDTO;
        }else {
            Employee emp = employeeRepository.findEmployeeByEmail(username);
            passportDTO = new Passport(emp.getLastName(),emp.getEmail(), emp.getEmployeeId(), null);
            return passportDTO;
        }
    }

    @Override
    public Passport getPassportFromOauth2Login(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttributes().get("email").toString();
        Users tmpUser = usersRepository.findUsersByUserEmail(email);
        Passport passportDTO = null;

        if(tmpUser == null){
            //若users找不到該使用者，則自動新增一個
            String userFirstName= oAuth2User.getAttributes().get("name").toString();
            String userLastName= oAuth2User.getAttributes().get("family_name").toString();
            Users user = new Users(
                    userLastName,          // 姓
                    userFirstName,         // 名
                    email,              // email
                    null,     // 預設密碼
                    null,           // 性别
                    null,            // 出生日期
                    0,              // 購買違規次數
                    new Date(),     // 最後登入時間
                    0,              // 賣家資格
                    null,       // 用戶狀態
                    null,       // 銀行帳號(3碼)
                    null     // 銀行帳號
            );
            tmpUser = usersRepository.save(user);
            passportDTO = new Passport(userLastName,email, tmpUser.getUserId(), "ROLE_USER");
            return passportDTO;

        }else{
            Integer sellerQualified = tmpUser.getSellerQualified();
            tmpUser.setLastLoginTime(new Date());
            usersRepository.save(tmpUser);

            passportDTO = new Passport(tmpUser.getLastName(),tmpUser.getUserEmail(), tmpUser.getUserId(),null);
            if(sellerQualified == 0){
                passportDTO.setRole("ROLE_USER");
            }else if(sellerQualified == 1){
                passportDTO.setRole("ROLE_SELLER");
            }else if(sellerQualified == 2){
                passportDTO.setRole("ROLE_SUGAR_SELLER");
            }
            return passportDTO;

        }
    }
}
