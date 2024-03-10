package com.ispan.projectX.controller.logIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogController {

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

}
