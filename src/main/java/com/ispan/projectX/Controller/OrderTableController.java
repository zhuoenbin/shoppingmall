package com.ispan.projectX.Controller;

import com.ispan.projectX.Service.OrderTableService;
import com.ispan.projectX.Service.ShoppingCartService;
import com.ispan.projectX.entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
public class OrderTableController {

    @Autowired
    OrderTableService otService;

    @Autowired
    private ShoppingCartService scService;

    // 抓前端傳送的資料 productId
    // 更新購物車
    @PostMapping("/order/add")
    public String updateCart(@RequestBody Map<String, List<Integer>> payload, Model model) {

        List<Integer> productIds = payload.get("productIds");

        // 使用購物車服務更新購物車
        List<ShoppingCart> sendOrder = scService.sendOrder(productIds);

        // 將更新後的購物車添加到Model中
        // 傳給前端 List<ShoppingCart>
        model.addAttribute("sendOrder", sendOrder);

        //商品為折扣總價 折扣價錢 優惠卷折扣 coupon
        Integer total = 0;

        for(ShoppingCart shoppingCart : sendOrder) {
            System.out.println(shoppingCart.getProduct().getSeller());
            total += shoppingCart.getProduct().getUnitPrice() * shoppingCart.getQuantity();
        }

        // 訂單總金額 還沒有折扣
        System.out.println(total);

        // 返回到order/sendOrder視圖 (不讓傳)
        return "orderTable/sendOrder";

    }




}
