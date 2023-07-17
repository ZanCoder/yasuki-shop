package com.poly.Yasuki.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/index"})
    public String viewHomePage(Model model){
        return "user/index";
    }
    @GetMapping("/list-product")
    public String viewListProductPage(Model model){
        return "user/listProduct";
    }
    @GetMapping("/cart")
    public String viewCartPage(Model model){
        return "user/cart";
    }
    @GetMapping("/news")
    public String viewNewsPage(Model model){
        return "user/news";
    }

    @GetMapping("/contact")
    public String viewContactPage(Model model){
        return "user/contact";
    }

    @GetMapping("/payment")
    public String viewPaymentPage(Model model){
        return "user/pay";
    }
    @GetMapping("/product")
    public String viewProductPage(Model model){
        return "user/product";
    }
    @GetMapping("/profile")
    public String viewProfilePage(Model model){
        return "user/profile";
    }
}
