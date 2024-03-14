package com.ispan.projectX.Controller;

import com.ispan.projectX.Service.EmployeeService;
import com.ispan.projectX.Service.ProductService;
import com.ispan.projectX.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService eSerivce;

    @Autowired
    private ProductService pSerivce;

    // 先做沒有登入的版本
    @GetMapping("/employee/verify")
    public String product(Model model) {

        List<Product> products = pSerivce.findVerify();

        // 將更新後的購物車添加到Model中
        // 傳給前端 List<ShoppingCart>
        model.addAttribute("verify", products);

        return "employee/verify";
    }

    @GetMapping("/employee/verifyButton")
    public String verify(@RequestParam Integer productId) {

        Product product = pSerivce.findById(productId);
        product.setProductStatus("Approved");

        pSerivce.insert(product);

        System.out.println(product);

        return "redirect:verify";
    }


}
