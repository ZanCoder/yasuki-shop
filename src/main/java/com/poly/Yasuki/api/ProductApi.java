package com.poly.Yasuki.api;


import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.service.OrderService;
import com.poly.Yasuki.service.ProductService;
import com.poly.Yasuki.utils.GlobalDataUtils;
import com.poly.Yasuki.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/detail-product")
    public Product viewProductPage(@RequestParam(name = "productId") Integer productId, Model model){
        Optional<Product> product = productService.findById(productId);
        return product.orElse(null);
    }

    @DeleteMapping("/admin/manager-product/delete")
    public ResponseEntity<?> doDeleteProduct(@RequestParam(name = "id") Integer id){
        try{
            productService.deleteById(id);
        }catch(Exception e){
            return ResponseEntity.status(500).body(MessageUtils.ERROR_FOREIGN_KEY);
        }
        return ResponseEntity.status(204).body("DELETED");
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
