package com.poly.Yasuki.controller;


import com.poly.Yasuki.entity.Evaluate;
import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.EvaluateService;
import com.poly.Yasuki.service.GroupCategoryService;
import com.poly.Yasuki.service.NewsAppService;
import com.poly.Yasuki.service.ProductService;
import com.poly.Yasuki.utils.SlugGenerator;
import lombok.RequiredArgsConstructor;
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
    private final NewsAppService newsAppService;



    @GetMapping("/")
    public String mainPage(Model model, HttpSession httpSession){
        List<GroupCategory> myCategoryList = groupCategoryService.getAllCategoryGroupIsActive();
        httpSession.setAttribute("dataCategory", myCategoryList);

        // set global category in session
        List<String> listBrand = productService.getAllBrand();
        httpSession.setAttribute("dataBrand", listBrand);

        model.addAttribute("listTopSelling", productService.getTopSelling());
        model.addAttribute("listTopDiscount", productService.getTopDiscount());
        model.addAttribute("listTopDateRelease", productService.getTopDateRelease());
        model.addAttribute("top5Newest", newsAppService.getTop5ByDateAndActive());
        model.addAttribute("listSkincare", productService.getListProductsByGroupId(myCategoryList.get(0).getId()));
        model.addAttribute("listMakeup", productService.getListProductsByGroupId(myCategoryList.get(1).getId()));
        model.addAttribute("listBodyCare", productService.getListProductsByGroupId(myCategoryList.get(2).getId()));
        return "user/index";
    }

    @GetMapping("/index")
    public String viewHomePage(Model model){
        return "user/index";
    }


    @GetMapping("/news")
    public String viewNewsPage(Model model){
        model.addAttribute("top5Newest", newsAppService.getTop5ByDateAndActive());
        return "user/news";
    }

    @GetMapping("/contact")
    public String viewContactPage(Model model){
        return "user/contact";
    }


    @GetMapping("/profile")
    public String viewProfilePage(Model model){
        return "user/profile";
    }

    @GetMapping("/product/compare")
    public String compareProductPage(Model model){
        return "user/compare_product";
    }

    public UserApp getCurrentUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        return userDetails.getUserApp();
    }

}
