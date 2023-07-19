package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.entity.CartItem;
import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.repo.CartItemRepo;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.ProductService;
import com.poly.Yasuki.utils.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepo cartItemRepo;
    private final ProductService productService;

    @Override
    public void addToCart(CartDto cartDto, UserApp userApp) {
        String slugProduct = SlugGenerator.generateSlug(cartDto.getNameProduct());
        Product product = productService.findBySlug(slugProduct);
        CartItem cartItem = new CartItem(cartDto.getQuantity(),product, userApp);

        // cart is exist | +1
        CartItem oldCart = getExistCartItem(userApp, product);
        if(oldCart != null){
            Integer newQuantity = oldCart.getQuantity() + 1;
            oldCart.setQuantity(newQuantity);
            cartItemRepo.save(oldCart);
        }else{ // new cart item
            cartItemRepo.save(cartItem);
        }
    }

    @Override
    public List<CartDto> getCartsByUser(UserApp currentUser) {
        List<CartItem> cartItemList = cartItemRepo.findByUserApp(currentUser);
        List<CartDto> cartDtoList = cartItemList.stream().map( cartItem -> {
            String nameProduct =  cartItem.getProduct().getName();
            BigDecimal priceProduct = cartItem.getProduct().getPrice();
            return new CartDto(cartItem.getQuantity(), nameProduct, priceProduct);
        }).collect(Collectors.toList());
        return cartDtoList;
    }

    private CartItem getExistCartItem(UserApp userApp, Product product) {
        CartItem cartItem = cartItemRepo.findByUserAppAndProduct(userApp, product);
        return cartItem;
    }
}
