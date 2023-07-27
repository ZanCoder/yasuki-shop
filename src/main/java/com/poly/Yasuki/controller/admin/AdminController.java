package com.poly.Yasuki.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin("*")
@Controller
@RequiredArgsConstructor
public class AdminController {


    @GetMapping("/admin/categories")
    public String  viewManagerCategoriesPage(Model model){
        return "admin/manager_category";
    }

    @GetMapping("/admin/orders")
    public String  viewManagerOrderPage(Model model){
        return "admin/manager_order";
    }
    @GetMapping("/admin/order/add")
    public String  viewAddOrderPage(Model model){
        return "admin/add_order";
    }
    @GetMapping("/admin/products")
    public String  viewManagerProductPage(Model model){
        return "admin/manager_product";
    }
    @GetMapping("/admin/product/add")
    public String  viewAddProductPage(Model model){
        return "admin/add_product";
    }
}
