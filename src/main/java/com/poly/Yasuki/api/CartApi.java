package com.poly.Yasuki.api;

import com.poly.Yasuki.entity.CartItem;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartApi {
    private final CartItemService cartItemService;
    @PostMapping( value = "/cart/add", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addToCart(
            @RequestParam(name = "productId") Integer productId,
            @RequestParam(name = "quantity") Integer quantity,
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            HttpSession session){
        if(myUserDetails != null){
            cartItemService.addToCart(quantity, productId, myUserDetails.getUserApp());
            session.setAttribute("sizeCart", cartItemService.getSize(myUserDetails.getUserApp()));
            return ResponseEntity.status(HttpStatus.OK).body(getSizeCart(myUserDetails.getUserApp()));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
    }
    @PostMapping("/cart/update")
    public ResponseEntity<?> updateCart(
            @RequestParam(name = "productId") Integer productId,
            @RequestParam(name = "action") String action,
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            HttpSession session){
        cartItemService.updateCartItem(action, productId, myUserDetails.getUserApp());
        session.setAttribute("sizeCart", cartItemService.getSize(myUserDetails.getUserApp()));
        return ResponseEntity.status(HttpStatus.OK).body(getSizeCart(myUserDetails.getUserApp()));
    }

    @DeleteMapping("/cart/delete")
    public ResponseEntity<?> deleteCart(
            @RequestParam(name = "productId") Integer productId,
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            HttpSession session){
        cartItemService.deleteCartItem( productId, myUserDetails.getUserApp());
        session.setAttribute("sizeCart", cartItemService.getSize(myUserDetails.getUserApp()));
        return ResponseEntity.status(HttpStatus.OK).body(getSizeCart(myUserDetails.getUserApp()));
    }
    public int getSizeCart(UserApp user){
        List<CartItem>  cartItemList = cartItemService.getCartsByUser(user);
        return cartItemList.size();
    }
}
