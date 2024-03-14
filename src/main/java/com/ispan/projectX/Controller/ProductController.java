package com.ispan.projectX.Controller;

import com.ispan.projectX.Service.ProductService;
import com.ispan.projectX.Service.ShoppingCartService;
import com.ispan.projectX.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService pService;

    @GetMapping("/")
    public String showProduct(@RequestParam(value="p", defaultValue = "1") Integer pageNumber, Model model ) {

        // 修改只有狀態為 Approved 和存貨不為 0 的商品能呈現
        Page<Product> page = pService.findByPage(pageNumber);

        model.addAttribute("page", page);

        return "index";
    }

}
