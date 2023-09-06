package com.poly.Yasuki.controller;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.dto.FeedBackDto;
import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.RoleApp;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.MyUserService;
import com.poly.Yasuki.service.RoleService;
import com.poly.Yasuki.service.SendEmailService;
import com.poly.Yasuki.utils.MessageUtils;
import com.poly.Yasuki.utils.RandomStringGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
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
    private final SendEmailService sendEmail;
    private final BCryptPasswordEncoder passwordEncoder;

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

    @PostMapping(value = "/forgot-password/send-code")
    @ResponseBody
    public String sendCodeForgotPassword(@RequestParam String email){
        String codeGenerate = RandomStringGenerator.generateRandomString(6);
        String bodySend = "Xin chào, mã xác nhận của bạn là : "+ codeGenerate;

        UserApp userApp =  myUserService.findByEmail(email);
        if(ObjectUtils.isEmpty(userApp)) return "NOT FOUND";
        myUserService.updatePassword(userApp, codeGenerate);
        try{
            sendEmail.sendMail(MessageUtils.SUBJECT_MAIL_FORGOT_PASSWORD,email, bodySend);
        }catch(Exception e) {
            return "ERROR";
        }
        return "OK";
    }

    @PostMapping(value = "/forgot-password")
    @ResponseBody
    public String forgotPassword(@RequestParam(name = "email") String email,
                                 @RequestParam(name = "code") String codeConfirm,
                                 @RequestParam(name = "newPass") String newPassword){

        UserApp userApp =  myUserService.findByEmail(email);
        if(ObjectUtils.isEmpty(userApp)) return "NOT FOUND";
        if(!passwordEncoder.matches(codeConfirm, userApp.getPassword())){
            return "NOT MATCH";
        }
        myUserService.updatePassword(userApp, newPassword);
        return "OK";
    }

    @PostMapping("/feedback")
    @ResponseBody
    public String sendFeedBack(@RequestBody FeedBackDto feedBackDto){
        String subject = "Phản hồi từ khách hàng!";
        String bodySendMail =
                "<ul>" +
                "  <li><strong>Tên khách hàng :</strong> "+ feedBackDto.getName() +"</li>" +
                "  <li><strong>SDT            :</strong> "+ feedBackDto.getPhoneNumber() +"</li>" +
                "  <li><strong>Địa chỉ        :</strong> "+ feedBackDto.getAddress() +"</li>" +
                "  <li><strong>Lời nhắn       :</strong> "+ feedBackDto.getContent() +"</li>" +
                "</ul>";

        try{
            sendEmail.sendMailHtml(subject, feedBackDto.getEmail(), bodySendMail);
        }catch(Exception e) {
            return "ERROR";
        }
        return "OK";
    }

}
