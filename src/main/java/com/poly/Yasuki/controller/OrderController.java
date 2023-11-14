package com.poly.Yasuki.controller;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.dto.OrderDto;
import com.poly.Yasuki.entity.Order;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.OrderService;
import com.poly.Yasuki.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order")
    public String viewOrderPage(Model model){
        return "user/pay";
    }
    @GetMapping("/order/history")
    public String viewOrderHistoryPage(
            @RequestParam(name = "status", required = false, defaultValue = "")String status,
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            Model model){
        List<Order> listOrder ;
        if(!status.equals("")){
            listOrder = orderService.findByUserAndStatus(myUserDetails.getUserApp(), status);
        }else{
            listOrder = orderService.findByUser(myUserDetails.getUserApp());
        }
        model.addAttribute("listOrder", listOrder);
        model.addAttribute("status", status);
        return "user/order_history";
    }
}
