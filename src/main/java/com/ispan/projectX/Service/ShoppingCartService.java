package com.ispan.projectX.Service;

import com.ispan.projectX.dao.ProductRepository;
import com.ispan.projectX.dao.ShoppingCartRepository;
import com.ispan.projectX.dao.UsersRepository;
import com.ispan.projectX.entity.Product;
import com.ispan.projectX.entity.ShoppingCart;
import com.ispan.projectX.entity.Users;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository scRepository;

    @Autowired
    private UsersRepository uRepository;

    @Autowired
    private ProductRepository pRepository;


    public void insertProduct(Integer productId) {
        Users user = uRepository.findByUserId(1);
        Product product = pRepository.findByProductId(productId);

        try {
            ShoppingCart existingCart = scRepository.findByUsersAndProduct(user, product);
            // 處理唯一结果
            // 購物車已存在，執行相應的邏輯
            existingCart.setQuantity(existingCart.getQuantity() + 1);
            existingCart.setUpdatedTime(new Date());
            scRepository.save(existingCart);
        }catch (NonUniqueResultException e) {
            // 處理多個結果的情況
            ShoppingCart shoppingCart = new ShoppingCart(user, product, 1, product.getUnitPrice(), new Date(), new Date());
            scRepository.save(shoppingCart);
        } catch (NullPointerException e) {
            // 處理NullPointerException
            ShoppingCart shoppingCart = new ShoppingCart(user, product, 1, product.getUnitPrice(), new Date(), new Date());
            scRepository.save(shoppingCart);
        }

    }
    public void updateQuantity(Integer shoppingCartId, Integer quantity) {

        ShoppingCart shoppingCart = scRepository.findByShoppingCartId(shoppingCartId);

        if(quantity != 0) {
            shoppingCart.setQuantity(quantity);
            shoppingCart.setUpdatedTime(new Date());
            scRepository.save(shoppingCart);
        }else {

            scRepository.delete(shoppingCart);

        }

    }

    public List<ShoppingCart> findAllCart(){
        return scRepository.findAll();
    }

    public List<ShoppingCart> findUserCart(Users user){

        return scRepository.findByUsers(user);

    }

    public Page<ShoppingCart> findByPage(Integer pageNumber) {

        Pageable pgb = PageRequest.of(pageNumber-1, 10,Sort.Direction.DESC, "createdTime");
        Page<ShoppingCart> page = scRepository.findAll(pgb);

        return page;
    }



}
