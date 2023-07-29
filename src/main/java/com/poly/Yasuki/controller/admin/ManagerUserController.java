package com.poly.Yasuki.controller.admin;

import com.poly.Yasuki.entity.RoleApp;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.enums.RoleName;
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
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ManagerUserController {
    private final MyUserService userService;
    private final RoleService roleService;
    private static final int ITEM_PER_PAGE = 5;

    @GetMapping("/admin")
    public String viewMainPage(){
        return "redirect:/admin/manager-user";
    }
    @GetMapping("/admin/manager-user")
    public String  viewManagerAccountPage(@RequestParam(name="page", defaultValue = "1", required = false)  int page,
                                          @RequestParam(name="sortBy",defaultValue = "id", required = false) String sortBy,
                                          @RequestParam(name="orderBy", defaultValue = "asc",  required = false) String orderBy,
                                          @RequestParam(name="keyword",  required = false) String keyword,
                                          Model model){
        Pageable pageable =
                PageRequest.of(page -1, ITEM_PER_PAGE).withSort(Sort.by(Sort.Direction.fromString(orderBy), sortBy));
        Page<UserApp> listUsers = null;
        if(keyword != null){
            listUsers = userService.findByKeyword(keyword, pageable);
            model.addAttribute("keyword", keyword);
        }else{
            listUsers = userService.getUsersWithSortAndPagination(pageable);
        }
        model.addAttribute("listUsers", listUsers.getContent());
        model.addAttribute("totalPages", 50);
        model.addAttribute("totalElements", listUsers.getTotalElements());
        model.addAttribute("currentPage", page);
        model.addAttribute("newUser", new UserApp());
        return "admin/manager_account";
    }
    @PostMapping("/admin/manager-user/add")
    public String doCreateNewUser(@ModelAttribute(name = "newUser") UserApp userApp,
                                  @RequestParam(name = "role_name", required = false) String roleName,
                                  Model model){
        try{
            RoleApp role = roleService.findRole(RoleName.valueOf(roleName));
            Set<RoleApp> roles = new HashSet<>();
            roles.add(role);
            userApp.setRoles(roles);
            userService.create(userApp);
            model.addAttribute("success", MessageUtils.ADD_SUCCESS);
        }catch(Exception ex){
            model.addAttribute("error", MessageUtils.ADD_FAILED);
        }
        return "redirect:/admin/manager-user";
    }

    @DeleteMapping("/admin/manager-user/delete")
    @ResponseBody
    public ResponseEntity<?> doDeleteUser(@RequestParam(name = "id") Integer id){
        try{
            userService.deleteUser(id);
        }catch(Exception e){
            return ResponseEntity.status(500).body(MessageUtils.ERROR_FOREIGN_KEY);
        }
        return ResponseEntity.status(204).body("DELETED");
    }

    @GetMapping("/admin/manager-user/edit")
    @ResponseBody
    public UserApp editUser(@RequestParam(name = "id") Integer id,
            Model model){
        model.addAttribute("mode", "edit");
        return userService.findById(id).get();
    }


}
