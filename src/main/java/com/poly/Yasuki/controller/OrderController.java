package com.poly.Yasuki.controller;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.dto.OrderDto;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.OrderService;
import com.poly.Yasuki.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final CartItemService cartItemService;
    private final SendEmailService sendEmail;
    private final TemplateEngine templateEngine;

    @GetMapping("/order")
    public String viewOrderPage(Model model){
        return "user/pay";
    }
    @GetMapping("/order/history")
    public String viewOrderHistoryPage(Model model){
        return "user/order_history";
    }

    @PostMapping("/order")
    @ResponseBody
    public String doOrder(@RequestBody OrderDto orderDtoList, HttpSession session
    ){
        orderService.create(orderDtoList, getCurrentUser());
        resetListCart(session);

        try {
            UserApp cureUserApp = getCurrentUser();
            sendEmail.sendMailWithInline(
                    cureUserApp.getEmail(),orderDtoList);
//            sendEmail.sendMailHtml(cureUserApp.getEmail(), SendEmailService.BODY_HTML);
            log.info("sent email for : {}", cureUserApp.getEmail());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "OK";
    }

    public UserApp getCurrentUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        return userDetails.getUserApp();
    }

    private void resetListCart(HttpSession session){
        List<CartDto> cartDtoList = cartItemService.getCartsByUser(getCurrentUser());
        session.setAttribute("listCart", cartDtoList);
    }


    private void testSendMail(){
        final Context ctx = new Context();
        ctx.setVariable("name", "luctuankietkg@gmail.com");
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
       // ctx.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML

        final String htmlContent = this.templateEngine.process("mail_order.html", ctx);

    }

}
