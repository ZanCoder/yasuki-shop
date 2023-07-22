package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.CartItem;
import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    CartItem findByUserAppAndProduct(UserApp userApp, Product product);

    List<CartItem> findByUserApp(UserApp userApp);

    void deleteByProductAndUserApp(Product product, UserApp currentUser);
}
