package com.poly.Yasuki.controller;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.MyUserService;
import com.poly.Yasuki.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartItemService cartItemService;
    private final ProductService productService;

    @GetMapping("/cart")
    public String viewCartPage(Model model){
        return "user/cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam("nameProduct") String nameProduct,
            @RequestParam("priceProduct") BigDecimal priceProduct, HttpSession session, Model model){
        CartDto cartDto = new CartDto(1,nameProduct, priceProduct);
        cartItemService.addToCart(cartDto, getCurrentUser());

        resetListCart(session);
        return "user/cart";
    }
    @DeleteMapping("/cart/delete")
    public String deleteCart(@RequestParam("nameProduct") String nameProduct, HttpSession session){
        cartItemService.deleteCartItem(nameProduct, getCurrentUser());

        resetListCart(session);
        return "user/cart";
    }

    @PostMapping("/cart/update")
    public String updateCart(@RequestParam(name = "action") String action,
                             @RequestParam(name = "nameProduct") String nameProduct, HttpSession session){
        cartItemService.updateCartItem(action, nameProduct, getCurrentUser());
        resetListCart(session);
        return "user/cart";
    }


    public  UserApp getCurrentUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        return userDetails.getUserApp();
    }

    private void resetListCart(HttpSession session){
        List<CartDto> cartDtoList = cartItemService.getCartsByUser(getCurrentUser());
        session.setAttribute("listCart", cartDtoList);
    }
}
