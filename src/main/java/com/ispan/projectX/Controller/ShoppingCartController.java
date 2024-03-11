package com.ispan.projectX.Controller;

import com.ispan.projectX.Service.ShoppingCartService;
import com.ispan.projectX.entity.Product;
import com.ispan.projectX.entity.ShoppingCart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService scService;

    @GetMapping("/shoppingCart/show")
    public String showProduct(@RequestParam(value="p", defaultValue = "1") Integer pageNumber, Model model ) {

        Page<ShoppingCart> page = scService.findByPage(pageNumber);

        model.addAttribute("page", page);

        return "shoppingCart/showProductPage";
    }

    @GetMapping("/shoppingCart/add")
    public String addShoppingCart(@RequestParam Integer productId) {

        scService.insertProduct(productId);

        System.out.println("成功加入商品");

        return "redirect:/";

    }

    @GetMapping("/shoppingCart/update")
    public String updateShoppingCart(@RequestParam(value="p", defaultValue = "1") Integer pageNumber,
                                     @RequestParam Integer shoppingCartId,
                                     @RequestParam Integer quantity,
                                     Model model) {

        scService.updateQuantity(shoppingCartId, quantity);

        Page<ShoppingCart> page = scService.findByPage(pageNumber);

        model.addAttribute("page", page);

        return "shoppingCart/showProductPage";

    }

}
