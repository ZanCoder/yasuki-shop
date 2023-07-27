package com.poly.Yasuki.controller;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.dto.OrderDto;
import com.poly.Yasuki.entity.Order;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CartItemService cartItemService;

    @GetMapping("/order")
    public String viewOrderPage(Model model){
        return "user/pay";
    }

    @PostMapping("/order")
    @ResponseBody
    public String doOrder(@RequestBody OrderDto orderDtoList, HttpSession session
    ){
        orderService.create(orderDtoList, getCurrentUser());
        resetListCart(session);
        return "OK";
    }

    public UserApp getCurrentUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        return userDetails.getUserApp();
    }

    private void resetListCart(HttpSession session){
        List<CartDto> cartDtoList = cartItemService.getCartsByUser(getCurrentUser());
        session.setAttribute("listCart", cartDtoList);
    }



}
