package com.poly.Yasuki.controller;


import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.service.GroupCategoryService;
import com.poly.Yasuki.service.MyCategoryService;
import com.poly.Yasuki.utils.GlobalSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final GroupCategoryService groupCategoryService;
    @GetMapping("/")
    public String mainPage(Model model, HttpSession httpSession){
        List<GroupCategory> myCategoryList = groupCategoryService.getAllCategoryGroupIsActive();
        httpSession.setAttribute("dataCategory", myCategoryList);
        return "user/index";
    }

    @GetMapping("/index")
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
