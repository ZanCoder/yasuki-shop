package com.poly.Yasuki.controller;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.entity.CartItem;
import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.MyUserService;
import com.poly.Yasuki.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartItemService cartItemService;
    private final ProductService productService;

    @GetMapping("/cart")
    public String mainPage(Model model, @AuthenticationPrincipal MyUserDetails userDetails, HttpSession session) {
        if(userDetails != null){
            List<CartItem> cartItemList = cartItemService.getCartsByUser(userDetails.getUserApp());
            model.addAttribute("listCart", cartItemList);
            session.setAttribute("sizeCart", cartItemService.getSize(userDetails.getUserApp()));
            return "user/cart";
        }
        return "login";
    }

}
