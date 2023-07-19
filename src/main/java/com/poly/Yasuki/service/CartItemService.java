package com.poly.Yasuki.service;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.entity.CartItem;
import com.poly.Yasuki.entity.UserApp;

import java.util.List;

public interface CartItemService {
    void addToCart(CartDto cartDto, UserApp userApp);

    List<CartDto> getCartsByUser(UserApp currentUser);
}
