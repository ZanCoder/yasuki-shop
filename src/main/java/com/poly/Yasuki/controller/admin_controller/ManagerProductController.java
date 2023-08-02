package com.poly.Yasuki.controller.admin_controller;

import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.entity.ProductImage;
import com.poly.Yasuki.service.GroupCategoryService;
import com.poly.Yasuki.service.MyCategoryService;
import com.poly.Yasuki.service.ProductService;
import com.poly.Yasuki.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ManagerProductController {
    private final ProductService productService;
    private final GroupCategoryService groupCategoryService;
    private final MyCategoryService categoryService;
    private static final int PRODUCT_PER_PAGE = 5;

    @GetMapping("/admin/manager-product")
    public String viewListProductPage(
            @RequestParam(name="page", defaultValue = "1", required = false)  int page,
            @RequestParam(name="sortBy",defaultValue = "id", required = false) String sortBy,
            @RequestParam(name="orderBy", defaultValue = "asc",  required = false) String orderBy,
            @RequestParam(name="keyword",  required = false) String keyword,
            Model model){
        Pageable pageable = PageRequest.of(page - 1, PRODUCT_PER_PAGE)
                .withSort(Sort.by(Sort.Direction.fromString(orderBy), sortBy));
        Page<Product> listProduct = null;
        if(keyword != null){
            listProduct = productService.findByKeyword(keyword, pageable);
            model.addAttribute("keyword", keyword);
        }else{
            listProduct = productService.getProductsWithSortAndPagination(pageable);
        }
        model.addAttribute("listProducts", listProduct.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", listProduct.getTotalPages());
        model.addAttribute("totalElements", listProduct.getTotalElements());
        return "admin/manager_product";
    }

    @GetMapping("/admin/manager-product/add")
    public String  viewAddProductPage(Model model){
        model.addAttribute("newProduct", new Product());
        model.addAttribute("groupCategoriesShow", groupCategoryService.getAll());
        model.addAttribute("indexGCSelected", 0);
        model.addAttribute("productImages", initListProductImage());
        return "admin/add_product";
    }

    @PostMapping("/admin/manager-product/add")
    public String doCreateNewProduct(@ModelAttribute(name = "newProduct") Product product,
                                        Model model){
        String mode = (String) model.getAttribute("mode");
        try{
            productService.create(product);
            model.addAttribute("success", MessageUtils.ADD_SUCCESS);
        }catch(Exception ex){
            model.addAttribute("error", MessageUtils.ADD_FAILED);
            return "/admin/add_product";
        }
        return "redirect:/admin/manager-product";
    }


    @DeleteMapping("/admin/manager-product/delete")
    @ResponseBody
    public ResponseEntity<?> doDeleteProduct(@RequestParam(name = "id") Integer id){
        try{
            productService.deleteById(id);
        }catch(Exception e){
            return ResponseEntity.status(500).body(MessageUtils.ERROR_FOREIGN_KEY);
        }
        return ResponseEntity.status(204).body("DELETED");
    }

    @GetMapping("/admin/manager-product/edit")
    public String editProduct(@RequestParam(name = "id") Integer id,
                                     Model model){
        Product product = productService.findById(id).get();
        model.addAttribute("mode", "edit");
        model.addAttribute("groupCategoriesShow", groupCategoryService.getAll());
//        model.addAttribute("categoriesSelected", groupCategoryService.getAll());
        model.addAttribute("newProduct", product);
        model.addAttribute("productImages", initListProductImage());
        model.addAttribute("indexGCSelected", productService.getCurrentIndexForGC(product));
        return "admin/add_product.html";
    }

    @PostMapping("/admin/manager-product/update")
    public String updateProduct(@ModelAttribute(name = "newProduct") Product product,
                                Model model){
        productService.update(product.getId(), product);
        return "redirect:/admin/manager-product";
    }


    @GetMapping("/admin/manager-product/change-status")
    @ResponseBody
    public ResponseEntity<?> changeStatusProduct(
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "statusChanged") Boolean statusChanged){
        productService.updateStatus(id, statusChanged);
        return ResponseEntity.status(200).body("UPDATED");
    }

    @GetMapping("/admin/manager-product/change-group-category")
    @ResponseBody
    public List<MyCategory> changeGroupCategoryProduct(
            @RequestParam(name = "id") Integer id){
        return categoryService.findByGroupCategoryId(id);
    }


    private  List<ProductImage> initListProductImage() {
        List<ProductImage> listProductImages = new ArrayList<>();
        listProductImages.add(new ProductImage(""));
        listProductImages.add(new ProductImage(""));
        listProductImages.add(new ProductImage(""));
        return listProductImages;
    }


}
