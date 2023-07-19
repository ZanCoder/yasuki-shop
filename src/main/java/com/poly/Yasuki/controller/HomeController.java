package com.poly.Yasuki.controller;


import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.GroupCategoryService;
import com.poly.Yasuki.service.MyCategoryService;
import com.poly.Yasuki.service.ProductService;
import com.poly.Yasuki.utils.GlobalSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final GroupCategoryService groupCategoryService;
    private final ProductService productService;
    private final CartItemService cartItemService;


    @GetMapping("/")
    public String mainPage(Model model, HttpSession httpSession){
        // global category
        List<GroupCategory> myCategoryList = groupCategoryService.getAllCategoryGroupIsActive();
        httpSession.setAttribute("dataCategory", myCategoryList);

        // global cart
        List<CartDto> cartDtoList = cartItemService.getCartsByUser(getCurrentUser());
        httpSession.setAttribute("listCart", cartDtoList);


        model.addAttribute("listTopSelling", productService.getTopSelling());
        model.addAttribute("listTopDiscount", productService.getTopDiscount());
        model.addAttribute("listTopDateRelease", productService.getTopDateRelease());
        return "user/index";
    }

    @GetMapping("/index")
    public String viewHomePage(Model model){
        return "user/index";
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

    @GetMapping("/profile")
    public String viewProfilePage(Model model){
        return "user/profile";
    }

    private UserApp getCurrentUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        return userDetails.getUserApp();
    }
}
