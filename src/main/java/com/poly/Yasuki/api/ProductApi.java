package com.poly.Yasuki.api;


import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.service.OrderService;
import com.poly.Yasuki.service.ProductService;
import com.poly.Yasuki.utils.GlobalDataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/detail-product")
    public Product viewProductPage(@RequestParam(name = "productId") Integer productId, Model model){
        Optional<Product> product = productService.findById(productId);
        return product.orElse(null);
    }

    @PostMapping("/product/data-compare")
    public List<Product> doCompareProduct(@RequestBody List<Integer> productIds, HttpSession session){
        List<Product> dataCompare = productIds.stream()
                .map(productId ->productService.findById(productId).get())
                .collect(Collectors.toList());
        session.setAttribute("dataCompare", dataCompare);
        session.setAttribute("titleCompare", GlobalDataUtils.getTitleCompare());
        return dataCompare;
    }

}
