package com.poly.Yasuki.controller.admin;

import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.RoleApp;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.enums.RoleName;
import com.poly.Yasuki.service.GroupCategoryService;
import com.poly.Yasuki.service.MyUserService;
import com.poly.Yasuki.service.RoleService;
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

import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ManagerGroupCategoriesController {
    private final GroupCategoryService groupCategoryService;
    private static final int ITEM_PER_PAGE = 5;
    @GetMapping("/admin/group-category")
    public String  viewManagerGroupCategoriesPage(
                @RequestParam(name="page", defaultValue = "1", required = false)  int page,
                @RequestParam(name="sortBy",defaultValue = "id", required = false) String sortBy,
                @RequestParam(name="orderBy", defaultValue = "asc",  required = false) String orderBy,
                @RequestParam(name="keyword",  required = false) String keyword,
                Model model){
        Pageable pageable = PageRequest.of(page -1, ITEM_PER_PAGE)
                        .withSort(Sort.by(Sort.Direction.fromString(orderBy), sortBy));
        Page<GroupCategory> liGroupCategories = null;
        if(keyword != null){
            liGroupCategories = groupCategoryService.findByKeyword(keyword, pageable);
            model.addAttribute("keyword", keyword);
        }else{
            liGroupCategories = groupCategoryService.getWithSortAndPagination(pageable);
        }
        model.addAttribute("listGroupCategories", liGroupCategories.getContent());
        model.addAttribute("totalPages", liGroupCategories.getTotalPages());
        model.addAttribute("totalElements", liGroupCategories.getTotalElements());
        model.addAttribute("currentPage", page);
        model.addAttribute("newGroupCategory", new GroupCategory());
        return "admin/manager_group_category";
    }
    @PostMapping("/admin/group-category/add")
    public String doCreateNewGroupCategories(@ModelAttribute(name = "groupCategory") GroupCategory groupCategory,
                                  Model model){
        try{
            groupCategoryService.create(groupCategory);
            model.addAttribute("success", MessageUtils.ADD_SUCCESS);
        }catch(Exception ex){
            model.addAttribute("error", MessageUtils.ADD_FAILED);
        }
        return "redirect:/admin/group-category";
    }

    @DeleteMapping("/admin/group-category/delete")
    @ResponseBody
    public ResponseEntity<?> doDeleteGroupCategories(@RequestParam(name = "id") Integer id){
        try{
            groupCategoryService.deleteById(id);
        }catch(Exception e){
            return ResponseEntity.status(500).body(MessageUtils.ERROR_FOREIGN_KEY);
        }
        return ResponseEntity.status(204).body("DELETED");
    }

    @GetMapping("/admin/group-category/edit")
    @ResponseBody
    public GroupCategory editGroupCategories(@RequestParam(name = "id") Integer id,
            Model model){
        model.addAttribute("mode", "edit");
        return groupCategoryService.findById(id).get();
    }

    @GetMapping("/admin/group-category/change-status")
    @ResponseBody
    public ResponseEntity<?> changeStatusGroupCategories(
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "statusChanged") Boolean statusChanged){
        groupCategoryService.updateStatus(id, statusChanged);
        return ResponseEntity.status(200).body("UPDATED");
    }


}
