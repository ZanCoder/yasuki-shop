package com.poly.Yasuki.api;

import com.poly.Yasuki.dto.FeedBackDto;
import com.poly.Yasuki.dto.MyCategoryDto;
import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.service.MyCategoryService;
import com.poly.Yasuki.service.MyUserService;
import com.poly.Yasuki.service.SendEmailService;
import com.poly.Yasuki.utils.MessageUtils;
import com.poly.Yasuki.utils.RandomStringGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class CategoryApi {
    private final MyCategoryService categoryService;

    @DeleteMapping("/admin/manager-category/delete")
    public ResponseEntity<?> doDeleteCategories(@RequestParam(name = "id") Integer id){
        try{
            categoryService.deleteById(id);
        }catch(Exception e){
            return ResponseEntity.status(500).body(MessageUtils.ERROR_FOREIGN_KEY);
        }
        return ResponseEntity.status(204).body("DELETED");
    }

    @GetMapping("/admin/manager-category/edit")
    public MyCategoryDto editCategories(@RequestParam(name = "id") Integer id,
                                        Model model){
        model.addAttribute("mode", "edit");
        MyCategoryDto categoryDto = new MyCategoryDto();
        MyCategory myCategory = categoryService.findById(id).get();
        BeanUtils.copyProperties(myCategory, categoryDto);
        categoryDto.setGroupCategory(myCategory.getGroupCategory().getId());
        return categoryDto;
    }

    @GetMapping("/admin/manager-category/change-status")
    public ResponseEntity<?> changeStatusCategories(
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "statusChanged") Boolean statusChanged){
        categoryService.updateStatus(id, statusChanged);
        return ResponseEntity.status(200).body("UPDATED");
    }
}
