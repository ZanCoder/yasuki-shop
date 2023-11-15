package com.poly.Yasuki.controller;

import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.EvaluateService;
import com.poly.Yasuki.service.ProductService;
import com.poly.Yasuki.utils.GlobalDataUtils;
import com.poly.Yasuki.utils.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CartItemService cartItemService;
    private final EvaluateService evaluateService;
    private static final int PRODUCT_PER_PAGE = 24;

    @GetMapping("/list-product")
    public String viewListProductPage(
            @RequestParam(name="page", defaultValue = "1", required = false)  int page,
            @RequestParam(name="sortBy",defaultValue = "id", required = false) String sortBy,
            @RequestParam(name="orderBy", defaultValue = "asc",  required = false) String orderBy,
            @RequestParam(name="category", defaultValue = "",  required = false) String categoryShow,
            @RequestParam(name="group-category", defaultValue = "",  required = false) String groupCategory,
            @RequestParam(name="keyword", defaultValue = "",  required = false) String keyword,
            Model model){
        Pageable pageable = PageRequest.of(page - 1, PRODUCT_PER_PAGE)
                .withSort(Sort.by(Sort.Direction.fromString(orderBy), sortBy));

        Page<Product> listProduct ;
        if(!categoryShow.equals("")){
            String slug = SlugGenerator.generateSlug(categoryShow);
            listProduct = productService.getListProductsByCategory(slug, pageable);
        }else if(!keyword.equals("")){
            listProduct = productService.findByKeywordAndActive(keyword, pageable);
        }else if(!groupCategory.equals("")){
            listProduct = productService.getListProductsByNameGroupCategory(groupCategory, pageable);
        }
        else{
            listProduct = productService.getAllAndActiveTrue(pageable);
        }
        Optional<String> breadcrumb = Arrays.asList(keyword,categoryShow, groupCategory)
                .stream().filter(item -> !item.equals("")).findFirst();
        model.addAttribute("listProduct", listProduct.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", listProduct.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("breadcrumb", breadcrumb.orElse(""));
        model.addAttribute("dataSort", sortBy + "-" + orderBy);
        return "user/listProduct";
    }

    @GetMapping("/detail-product/{slug}")
    public String viewProductPage(@PathVariable(name = "slug") String slug,  Model model){
        Product product = productService.findBySlug(slug);
        model.addAttribute("product", product);
        model.addAttribute("sameProduct", getListProductsByCategory(product.getCategory().getSlug()));
        model.addAttribute("evaluateList", evaluateService.findByProduct(product));
        model.addAttribute("titleCompare", GlobalDataUtils.getTitleCompare());
        return "user/product";
    }
    private List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    // get product have the same category with main product
    private Page<Product> getListProductsByCategory(String categorySlug){
        return productService.getListProductsByCategory(categorySlug, PageRequest.of(0, 12));
    }
}
