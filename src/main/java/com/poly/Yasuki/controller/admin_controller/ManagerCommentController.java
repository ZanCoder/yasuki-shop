package com.poly.Yasuki.controller.admin_controller;

import com.poly.Yasuki.entity.Evaluate;
import com.poly.Yasuki.service.EvaluateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ManagerCommentController {
    private final int ITEM_PER_PAGE = 10;

    private final EvaluateService evaluateService;

    @GetMapping("/admin/manager-comment/{productId}")
    public String viewListCommentPage(
            @PathVariable(name = "productId") Integer productId,
            @RequestParam(name="page", defaultValue = "1", required = false)  int page,
            @RequestParam(name="keyword",  required = false) String keyword,
                                      Model model ){
        Page<Evaluate> evaluateList = null;
                Pageable pageable = PageRequest.of(page - 1, ITEM_PER_PAGE);
        if(keyword != null){
            evaluateList = evaluateService.findByKeyword(keyword, productId, pageable);
            model.addAttribute("keyword", keyword);
        }else{
            evaluateList = evaluateService.getCommentsWithPagination(productId, pageable);
        }
        model.addAttribute("productId", productId);
        if(evaluateList != null){
            model.addAttribute("listComments", evaluateList.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", evaluateList.getTotalPages());
            model.addAttribute("totalElements", evaluateList.getTotalElements());
            return "admin/manager_comment";
        }else{
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", 0);
            model.addAttribute("totalElements", 0);
            return "admin/manager_comment";
        }
    }
}
