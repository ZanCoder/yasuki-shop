package com.poly.Yasuki.controller.admin;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.dto.OrderDto;
import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.entity.Order;
import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.GroupCategoryService;
import com.poly.Yasuki.service.OrderService;
import com.poly.Yasuki.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ManagerOrderController {
    private final OrderService orderService;
    private final GroupCategoryService groupCategoryService;
    private static final int PRODUCT_PER_PAGE = 5;

    @GetMapping("/admin/manager-order")
    public String  viewManagerOrderPage(
                             @RequestParam(name="page", defaultValue = "1", required = false)  int page,
                             @RequestParam(name="sortBy",defaultValue = "id", required = false) String sortBy,
                             @RequestParam(name="orderBy", defaultValue = "asc",  required = false) String orderBy,
                             @RequestParam(name="keyword",  required = false) String keyword,
                             Model model){
        Pageable pageable = PageRequest.of(page - 1, PRODUCT_PER_PAGE)
                .withSort(Sort.by(Sort.Direction.fromString(orderBy), sortBy));
        Page<Order> listOrders = null;
        if(keyword != null){
            listOrders = orderService.findByKeyword(keyword, pageable);
            model.addAttribute("keyword", keyword);
        }else{
            listOrders = orderService.getWithSortAndPagination(pageable);
        }
        model.addAttribute("listOrders", listOrders.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", listOrders.getTotalPages());
        model.addAttribute("totalElements", listOrders.getTotalElements());

        return "admin/manager_order";
    }
    @GetMapping("/admin/manager-order/add")
    public String  viewAddOrderPage(Model model){
        model.addAttribute("newOrder", new Order());
        model.addAttribute("groupCategories", groupCategoryService.getAll());
        return "admin/add_order";
    }

    @PostMapping("/admin/manager-order/update")
    public String doCreateNewOrder(@ModelAttribute(name = "newOrder") Order order,
                                     Model model){
        try{
            orderService.insert(order);
            model.addAttribute("success", MessageUtils.ADD_SUCCESS);
        }catch(Exception ex){
            model.addAttribute("error", MessageUtils.ADD_FAILED);
            return "/admin/add_order";
        }
        return "redirect:/admin/manager-order";
    }

    @DeleteMapping("/admin/manager-order/delete")
    @ResponseBody
    public ResponseEntity<?> doDeleteOrder(@RequestParam(name = "id") Integer id){
        try{
            orderService.deleteById(id);
        }catch(Exception e){
            return ResponseEntity.status(500).body(MessageUtils.ERROR_FOREIGN_KEY);
        }
        return ResponseEntity.status(204).body("DELETED");
    }

    @GetMapping("/admin/manager-order/edit")
    public String editOrder(@RequestParam(name = "id") Integer id,
                              Model model){
        Order order = orderService.findById(id).get();
        model.addAttribute("mode", "edit");
        model.addAttribute("editOrder", order);
        model.addAttribute("groupCategories", groupCategoryService.getAll());
        return "/admin/add_order";
    }

}
