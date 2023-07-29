package com.poly.Yasuki.api;


import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.service.OrderService;
import com.poly.Yasuki.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductApi {
    private final ProductService productService;

    @GetMapping(value = "/admin/manager-order/find-by-keyword", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<Product> findListProdToOrderByKeyword(
            @RequestParam(name = "keyword") String keyword,
            Model model){
        return productService.findByKeyword(keyword);
    }
    @GetMapping(value = "/admin/manager-order/find-by-group", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<Product> findListProdToOrderByGroup(
            @RequestParam(name = "id") Integer id,
            Model model){
        return productService.getListProductsByGroupId(id);
    }
    @GetMapping(value = "/admin/manager-order/find-by-category", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<Product> findListProdToOrderCategory(
            @RequestParam(name = "id") Integer id,
            Model model){
        return productService.getListProductsByCategoryId(id);
    }

}
