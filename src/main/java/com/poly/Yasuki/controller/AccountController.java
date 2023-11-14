package com.poly.Yasuki.controller;

import com.poly.Yasuki.dto.FeedBackDto;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.MyUserService;
import com.poly.Yasuki.service.SendEmailService;
import com.poly.Yasuki.utils.MessageUtils;
import com.poly.Yasuki.utils.RandomStringGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class AccountController {



    @GetMapping("/login")
    String viewLoginForm() {
        return "login";
    }
}
