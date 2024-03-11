package com.ispan.projectX.controller.logIO;

import com.ispan.projectX.service.interfacefile.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

//頁面導向的controller，純測試，之後會棄用
@Controller
public class LogController {

    private AccountService accountService;

    @Autowired
    public LogController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/loginPage")
    public String showLoginPage() {
        return "fancy-login"; // 返回login.html作為視圖名稱
    }

    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }

    @GetMapping ("/logoutPage")
    public String showLogoutPage() {
        return "test-logout";
    }

    @GetMapping ("/mainPage")
    public String showMainPage() {
        return "main";
    }

    @GetMapping ("/registerPage")
    public String showRegisterPage() {
        return "register";
    }


    @GetMapping ("/forgetPassword/forgetPasswordPage")
    public String showForgetPasswordPage() {
        return "forgetPassword";
    }
//    @GetMapping("/forgetPassword/verifyPage")
//    public String showVerifyPage() {
//
//            return "verify-code-forgetPassword";
//
//    }

    @PostMapping("/forgetPassword/verifyPage")
    public String showVerifyPage(@RequestParam("email") String email, Model model) {
        if(!accountService.checkEmailIsEmpty(email)){
            model.addAttribute("email", email);
            accountService.sendCodeForResetPassword(email);
            System.out.println(email);
            return "verify-code-forgetPassword";
        }
        return "forgetPassword";
    }

    @PostMapping("/forgetPassword/verifyCode")
    public String verifyCode(@RequestParam("email") String email,
                             @RequestParam("verificationCode") String verificationCode,
                             Model model) {
        // 在這裡進行驗證碼的驗證
        boolean isValid = accountService.verifyCodeForResetPassword(email, verificationCode);
        if (isValid) {
            // 驗證碼正確，導向重設密碼頁面
            model.addAttribute("email", email);
            return "resetPassword";
        } else {
            // 驗證碼錯誤，返回到驗證頁面並顯示錯誤訊息
            model.addAttribute("email", email);
            model.addAttribute("error", "驗證碼錯誤，請重新輸入");
            return "verify-code-forgetPassword";
        }
    }
}
