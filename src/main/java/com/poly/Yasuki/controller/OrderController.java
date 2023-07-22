package com.poly.Yasuki.controller;

import com.poly.Yasuki.entity.Order;
import com.poly.Yasuki.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order")
    public String viewOrderPage(Model model){
        return "user/pay";
    }



}
