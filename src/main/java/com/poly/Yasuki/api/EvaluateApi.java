package com.poly.Yasuki.api;

import com.poly.Yasuki.dto.EvaluateDto;
import com.poly.Yasuki.entity.Evaluate;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.EvaluateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EvaluateApi {

    private final EvaluateService evaluateService;

    @PostMapping("/rate")
    public String viewHomePage(@RequestBody EvaluateDto evaluateDto, Model model){
        try {
            evaluateService.create(evaluateDto, getCurrentUser());
            return "OK";
        }catch (Exception e){
            return "UN_AUTHORIZATION";
        }

    }

    public UserApp getCurrentUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        return userDetails.getUserApp();
    }
}
