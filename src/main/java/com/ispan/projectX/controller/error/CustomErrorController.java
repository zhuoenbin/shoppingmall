package com.ispan.projectX.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


//大殺器，任何error或exception都會被導到這裡

//@Controller
//public class CustomErrorController implements ErrorController {
//
//    @RequestMapping("/error")
//    public String handleError() {
//
//        return "fancy-login";
//    }
//
//
//    public String getErrorPath() {
//        return "/error";
//    }
//}
