package com.ispan.projectX.controller.logIO;

import com.ispan.projectX.dao.EmployeeRepository;
import com.ispan.projectX.dao.UsersRepository;
import com.ispan.projectX.dto.Passport;
import com.ispan.projectX.entity.Users;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class LogRestController {

    private EmployeeRepository employeeRepository;

    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public LogRestController(EmployeeRepository employeeRepository, UsersRepository usersRepository,PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
        this.usersRepository = usersRepository;
    }



//    @GetMapping("/oauthLogin")
//    public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        // 获取当前认证对象的权限列表
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//
//        // 创建一个新的权限列表，添加原始权限和额外的 ROLE_USER 权限
//        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(authorities);
//        updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//        // 创建一个新的 Authentication 对象，包含新的权限列表
//        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), updatedAuthorities);
//
//        // 将新的 Authentication 对象设置为当前认证对象
//        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
//        return oAuth2User.getAttributes();
//    }

    @GetMapping("/oauthLogin")
    public String getUser(@AuthenticationPrincipal OAuth2User oAuth2User, HttpSession session) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 獲得權限列表
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // 創造新列表，並且加入ROLE_XXX的權限
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(authorities);

        //確認oauth登入會員是否是本站會員
        String email = oAuth2User.getAttributes().get("email").toString();
        Users tmpUser = usersRepository.findUsersByUserEmail(email);
        Passport passportDTO = null;

        if(tmpUser == null){
            //若users找不到該使用者，則自動新增一個
            String userFirstName= oAuth2User.getAttributes().get("name").toString();
            String userLastName= oAuth2User.getAttributes().get("family_name").toString();
            //String userPassWord = passwordEncoder.encode(authentication.getName());


            updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
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
            session.setAttribute("passportDTO", passportDTO);
        }else{
            Integer sellerQualified = tmpUser.getSellerQualified();
            tmpUser.setLastLoginTime(new Date());
            usersRepository.save(tmpUser);

            if(sellerQualified == 0){
                updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }else if(sellerQualified == 1){
                updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
            }else if(sellerQualified == 2){
                updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_SUGAR_SELLER"));
            }
            String UserAuthority = updatedAuthorities.get(updatedAuthorities.size() - 1).getAuthority();
            passportDTO = new Passport(tmpUser.getLastName(),tmpUser.getUserEmail(), tmpUser.getUserId(), UserAuthority);
            session.setAttribute("passportDTO", passportDTO);

        }

        // 创建一个新的 Authentication 对象，包含新的权限列表
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), updatedAuthorities);

        // 将新的 Authentication 对象设置为当前认证对象
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        Collection<? extends GrantedAuthority> oauthorities = newAuthentication.getAuthorities();
        String lastAuthority = null;
        if (!oauthorities.isEmpty()) {
            lastAuthority = ((List<? extends GrantedAuthority>) oauthorities).get(oauthorities.size() - 1).getAuthority();
        }

        return passportDTO.toString();
    }

    @GetMapping("/userLogin")
    public String userLogin(Authentication authentication, HttpSession session){

        // 取得使用者的帳號(email
        String username = authentication.getName();
        // 取得使用者的權限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String authority = authorities.iterator().next().getAuthority();

        //確認是user或是employee
        if(usersRepository.findUsersByUserEmail(username) != null){
            Users tmpUser = usersRepository.findUsersByUserEmail(username);
            tmpUser.setLastLoginTime(new Date());
            usersRepository.save(tmpUser);
            Passport passportDTO = new Passport(tmpUser.getLastName(),username, tmpUser.getUserId(), authority);
            session.setAttribute("passportDTO", passportDTO);
            return "Hello 會員: " + username + "你好 ! 你的權限為: " + authorities + passportDTO;
        }else if (employeeRepository.findEmployeeByEmail(username) != null){
            Passport passportDTO = new Passport(employeeRepository.findEmployeeByEmail(username).getLastName(),username, employeeRepository.findEmployeeByEmail(username).getEmployeeId(), authority);
            session.setAttribute("passportDTO", passportDTO);
            return "Hello 員工: " + username + "你好 ! 你的權限為: " + authorities + passportDTO;
        }else {
            return "查無此人" + username;
        }

    }
//    @GetMapping("/memberlogout")
//    public String logout() {
//        // 执行登出逻辑
//        SecurityContextHolder.clearContext(); // 清除用户上下文
//
//        // 返回登出成功的消息或重定向到登出成功页面
//        return "You have been logged out successfully.";
//    }




    private List<GrantedAuthority> convertToAuthority(String memberAuthority){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(memberAuthority));
        return authorities;
    }
}
