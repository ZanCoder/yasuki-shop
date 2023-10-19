package com.poly.Yasuki.controller.admin_controller;

import com.poly.Yasuki.dto.DiscountItemDto;
import com.poly.Yasuki.entity.DiscountItem;
import com.poly.Yasuki.service.DiscountService;
import com.poly.Yasuki.service.MyCategoryService;
import com.poly.Yasuki.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ManagerDiscountController {
    private final DiscountService discountService;
    private final MyCategoryService categoryService;
    @GetMapping("/admin/manager-discount")
    public String viewPageManagerDiscount(Model model){
        List<DiscountItem> discountItemList = discountService.getAll();

        model.addAttribute("listCategories", categoryService.getAllCategory());
        model.addAttribute("listDiscount", discountItemList);
        model.addAttribute("newDiscount", new DiscountItem(new Timestamp(new Date().getTime())));
        return "admin/manager_discount";
    }


    @PostMapping("/admin/manager-discount/create")
    public String createDiscount(@ModelAttribute(name = "newDiscount") DiscountItem discountItem,
            Model model){
        try{
            discountService.create(discountItem);
            model.addAttribute("success", MessageUtils.ADD_SUCCESS);
        }catch(Exception ex){
            model.addAttribute("error", MessageUtils.ADD_FAILED);
        }
        return "redirect:/admin/manager-discount";
    }

    @GetMapping("/admin/manager-discount/edit")
    @ResponseBody
    public DiscountItemDto editDiscountItem(@RequestParam(name = "id") Integer id,
                                         Model model){
        model.addAttribute("mode", "edit");
        DiscountItemDto discountItemDto = new DiscountItemDto();
        DiscountItem discountItem = discountService.findById(id).get();
        BeanUtils.copyProperties(discountItem, discountItemDto);
        discountItemDto.setCategory(discountItem.getCategory().getId());
        discountItemDto.setDateCreateString(discountItem.getDateCreate().toString());
        return discountItemDto;
    }

    @DeleteMapping("/admin/manager-discount/delete")
    @ResponseBody
    public ResponseEntity<?> doDeleteDiscountItem(@RequestParam(name = "id") Integer id){
        try{
            discountService.deleteById(id);
        }catch(Exception e){
            return ResponseEntity.status(500).body(MessageUtils.ERROR_FOREIGN_KEY);
        }
        return ResponseEntity.status(204).body("DELETED");
    }


}
