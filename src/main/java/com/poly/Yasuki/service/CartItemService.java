package com.poly.Yasuki.service;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.entity.CartItem;
import com.poly.Yasuki.entity.UserApp;

import java.util.List;

public interface CartItemService {
    void addToCart(Integer quantity, Integer productId, UserApp userApp);

    List<CartItem> getCartsByUser(UserApp currentUser);

    void deleteCartItem(Integer productId, UserApp currentUser);

    void updateCartItem(String action, Integer productId, UserApp currentUser);

    int getSize(UserApp userApp);
}
