package com.poly.Yasuki.api;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.dto.OrderDto;
import com.poly.Yasuki.dto.OrderItemResponse;
import com.poly.Yasuki.dto.ProductResponse;
import com.poly.Yasuki.entity.CartItem;
import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.OrderService;
import com.poly.Yasuki.service.ProductService;
import com.poly.Yasuki.service.SendEmailService;
import com.poly.Yasuki.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderApi {
    private final OrderService orderService;
    private final CartItemService cartItemService;
    private final ProductService productService;
    private final SendEmailService sendEmail;
    @PostMapping("/get-data-order")
    public List<OrderItemResponse> getDataOrder(
            @RequestBody List<CartDto> cartDtoList
    ){
        List<OrderItemResponse> listCartItemOrder = cartDtoList
                .stream().map(cartDto -> {
                    Product product = productService.findById(cartDto.getProductId()).get();
                    OrderItemResponse orderItemResponse = new OrderItemResponse();
                    orderItemResponse.setProductId(cartDto.getProductId());
                    orderItemResponse.setQuantity(cartDto.getQuantity());
                    orderItemResponse.setName(product.getName());
                    orderItemResponse.setPrice(product.getPriceDiscount());
                    return orderItemResponse;
                }).collect(Collectors.toList());
        return listCartItemOrder;
    }
    @GetMapping(value = "/admin/manager-order/find-by-group", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<ProductResponse> findListProdToOrderByGroup(
            @RequestParam(name = "id") Integer id){
        List<ProductResponse> productResponseList = productService.getListProductsByGroupId(id)
                .stream().map(product ->  new ProductResponse(
                                product.getId(),
                                product.getName(),
                                product.getQuantityLeft(),
                                product.getPriceDiscount()
                        )
                ).collect(Collectors.toList());
        return productResponseList;
    }
    @GetMapping(value = "/admin/manager-order/find-by-category", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<ProductResponse> findListProdToOrderCategory(
            @RequestParam(name = "id") Integer id){
        List<ProductResponse> productResponseList = productService.getListProductsByCategoryId(id)
                .stream().map(product ->  new ProductResponse(
                                product.getId(),
                                product.getName(),
                                product.getQuantityLeft(),
                                product.getPriceDiscount()
                        )
                ).collect(Collectors.toList());
        return productResponseList;
    }
    @GetMapping(value = "/admin/manager-order/find-by-keyword", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<ProductResponse> findListProdToOrderByKeyword(
            @RequestParam(name = "keyword") String keyword){
        List<ProductResponse> productResponseList = productService.findByKeyword(keyword)
                .stream().map(product ->  new ProductResponse(
                                product.getId(),
                                product.getName(),
                                product.getQuantityLeft(),
                                product.getPriceDiscount()
                        )
                ).collect(Collectors.toList());
        return productResponseList;
    }

    @DeleteMapping("/admin/manager-order/delete")
    public ResponseEntity<?> doDeleteOrder(@RequestParam(name = "id") Integer id){
        try{
            orderService.deleteById(id);
        }catch(Exception e){
            return ResponseEntity.status(500).body(MessageUtils.ERROR_FOREIGN_KEY);
        }
        return ResponseEntity.status(204).body("DELETED");
    }

    @PostMapping("/admin/manager-order/add")
    public String doCreateNewOrder(
            @RequestBody OrderDto orderDtoList, HttpSession session,
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ){
        orderService.create(orderDtoList, myUserDetails.getUserApp());
        getSizeCart(myUserDetails.getUserApp());
        return "OK";
    }

    @PostMapping("/order")
    public String doOrder(
            @RequestBody OrderDto orderDtoList,
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            HttpSession session
    ){
        if(myUserDetails != null){
            orderService.create(orderDtoList, myUserDetails.getUserApp());
            try {
                UserApp cureUserApp = myUserDetails.getUserApp();
                sendEmail.sendMailWithInline(
                        cureUserApp.getEmail(),orderDtoList);
                log.info("sent email for : {}", cureUserApp.getEmail());
            } catch (MessagingException | UnsupportedEncodingException e ) {
                log.info("Email have problem! - {}", e.getMessage());
            }
        }
        return "OK";
    }

    public int getSizeCart(UserApp user){
        List<CartItem>  cartItemList = cartItemService.getCartsByUser(user);
        return cartItemList.size();
    }
}
