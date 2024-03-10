package com.ispan.projectX.controller.logIO;

import com.ispan.projectX.dao.EmployeeRepository;
import com.ispan.projectX.dao.UsersRepository;
import com.ispan.projectX.dto.Passport;
import com.ispan.projectX.entity.Users;
import com.ispan.projectX.service.interfacefile.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class LogRestController {


    private AccountService accountService;



    @Autowired
    public LogRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register/formRegister")
    public ResponseEntity<String> registerUser(@ModelAttribute Users user) {
        System.out.println(user.toString());
        try {
            // 調用userService的註冊方法(密碼自動加密)
            accountService.register(user);
            return ResponseEntity.ok("註冊成功");
        } catch (Exception e) {
            // 處理異常情況
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("註冊失敗：" + e.getMessage());
        }
    }

    //給postman用，可直接JSON註冊
    @PostMapping("/register/restRegister")
    public ResponseEntity<String> restRegisterUser(@RequestBody Users user) {

        // 檢查 email 是否已經存在
        if (!accountService.checkEmailIsEmpty(user.getUserEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("此 Email 已被註冊");
        }

        user.setLastLoginTime(new Date());
        // 保存用戶
        accountService.register(user);
        return ResponseEntity.ok("註冊成功");

    }


    @GetMapping("/oauthLogin")
    public String getUser(@AuthenticationPrincipal OAuth2User oAuth2User, HttpSession session) {

        Passport passportDTO = accountService.getPassportFromOauth2Login(oAuth2User);

        // 獲得oauth2原始權限列表
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // 創造新列表，並且加入ROLE_XXX的權限(自定義權限)
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(authorities);
        updatedAuthorities.add(new SimpleGrantedAuthority(passportDTO.getRole()));
        // new一個新的Authentication，包含就得與新的權限
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), updatedAuthorities);
        // 把新的Authentication更新到oauth2的權限
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        Collection<? extends GrantedAuthority> oauthorities = newAuthentication.getAuthorities();

        session.setAttribute("passportDTO", passportDTO);
        return passportDTO.toString()+"========="+oauthorities.toString();
    }

    @GetMapping("/userLogin")
    public String userLogin(Authentication authentication, HttpSession session){

        // 取得使用者的帳號(email)
        String username = authentication.getName();
        // 取得使用者的權限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String authority = authorities.iterator().next().getAuthority();

        //核發通行證&存入httpsession
        Passport passportDTO = accountService.getPassportFromFormLogin(username);
        passportDTO.setRole(authority);
        session.setAttribute("passportDTO", passportDTO);

        return "Hello  " + passportDTO.getUsername() + "先生/女士 你好 ! 你的權限為: " +  passportDTO.getRole();

    }





    private List<GrantedAuthority> convertToAuthority(String memberAuthority){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(memberAuthority));
        return authorities;
    }
}
