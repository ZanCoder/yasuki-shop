package com.poly.Yasuki.service;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.entity.UserApp;

import java.util.List;

public interface CartItemService {
    void addToCart(CartDto cartDto, UserApp userApp);

    List<CartDto> getCartsByUser(UserApp currentUser);

    void deleteCartItem(String nameProduct, UserApp currentUser);

    void updateCartItem(String action, String nameProduct, UserApp currentUser);
}
