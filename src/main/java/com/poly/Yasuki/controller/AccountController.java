package com.poly.Yasuki.controller;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.RoleApp;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.MyUserService;
import com.poly.Yasuki.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final MyUserService myUserService;
    private final CartItemService cartItemService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/login")
    String viewLoginForm() {
        return "login";
    }

    @PostMapping("/login-with-ajax")
    @ResponseBody
    public String loginWithModal(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            HttpServletRequest request, HttpServletResponse response
    ) {

        try{
            Boolean checkLogin = myUserService.doLogin(username, password);
            if(checkLogin){
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                List<CartDto> cartDtoList =
                        cartItemService.getCartsByUser
                                (((MyUserDetails) authentication.getPrincipal()).getUserApp());
                request.getSession().setAttribute("listCart", cartDtoList);

            }else{
                return "INVALID";
            }
        }catch(UsernameNotFoundException ex){
            ex.getMessage();
            return "NOT FOUND";
        }
        return "OK";
    }


    @PostMapping(value = "/signup-with-ajax")
    @ResponseBody
    public String loginWithModal(@RequestBody UserApp userApp){
        try{
            myUserService.create(userApp);
        }catch(Exception e) {
            return "ALREADY EXIST";
        }
        return "OK";
    }

}
