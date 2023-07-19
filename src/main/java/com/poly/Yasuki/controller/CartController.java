package com.poly.Yasuki.controller;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.entity.CartItem;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartItemService cartItemService;
    private final MyUserService userService;

    @GetMapping("/cart")
    public String viewCartPage(Model model){
        return "user/cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(CartDto cartDto){
        cartItemService.addToCart(cartDto, getCurrentUser());
        return "user/cart";
    }

    private UserApp getCurrentUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        return userDetails.getUserApp();
    }

}
