package com.ispan.projectX.config.securityconfig;

import com.ispan.projectX.dao.EmployeeRepository;
import com.ispan.projectX.dao.UsersRepository;

import com.ispan.projectX.entity.Employee;
import com.ispan.projectX.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailService implements UserDetailsService {

    private EmployeeRepository employeeRepository;

    private UsersRepository usersRepository;


    public MyUserDetailService() {
    }

    @Autowired
    public MyUserDetailService(EmployeeRepository employeeRepository, UsersRepository usersRepository) {
        this.employeeRepository = employeeRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Employee theEmployee = employeeRepository.findEmployeeByEmail(username);
        Users theUser = usersRepository.findUsersByUserEmail(username);

        if (theEmployee != null) {
            String memberEmail = theEmployee.getEmail();
            String memberPassword = theEmployee.getPassword();
            String memberAuthority = theEmployee.getDbAuthority();

            // 權限部分
            GrantedAuthority authority = new SimpleGrantedAuthority(memberAuthority);
            // 轉換成 Spring Security 指定的 User 格式
            List<GrantedAuthority> authorities = convertToAuthority(memberAuthority);

            return new User(memberEmail, memberPassword, authorities);

        } else if(theUser != null){
            String memberEmail = theUser.getUserEmail();
            String memberPassword = theUser.getUserPassword();
            String memberAuthority = null;
            Integer memberSellerQualified = theUser.getSellerQualified();
            if(memberSellerQualified == 1){
                memberAuthority = "ROLE_SELLER";
            }else if(memberSellerQualified == 2){
                memberAuthority = "ROLE_SUGAR_SELLER";
            }else{
                memberAuthority = "ROLE_USER";
            }

//            GrantedAuthority authority = new SimpleGrantedAuthority(memberAuthority);
            List<GrantedAuthority> authorities = convertToAuthority(memberAuthority);
//            System.out.println(memberEmail);
//            System.out.println(memberPassword);
//            System.out.println(authorities);

            return new User(memberEmail, memberPassword, authorities);

        }else{
            throw new UsernameNotFoundException("Member not found for: " + username);
        }
    }

    private List<GrantedAuthority> convertToAuthority(String memberAuthority){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(memberAuthority));
        return authorities;
    }
}
