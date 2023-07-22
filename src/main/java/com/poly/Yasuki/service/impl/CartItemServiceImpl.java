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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepo cartItemRepo;
    private final ProductService productService;

    @Override
    public void addToCart(CartDto cartDto, UserApp userApp) {
        Product product = getProductByName(cartDto.getNameProduct());
        CartItem cartItem = new CartItem(cartDto.getQuantity(),product, userApp);
        // cart is exist | + 1
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


    @Override
    public void deleteCartItem(String productName, UserApp currentUser) {
        Product product = getProductByName(productName);
        cartItemRepo.deleteByProductAndUserApp(product, currentUser);
    }

    @Override
    public void updateCartItem(String action, String nameProduct, UserApp currentUser) {
        CartItem currentCart =  cartItemRepo.findByUserAppAndProduct(currentUser, getProductByName(nameProduct));
        if(action.equals("increase")){
            currentCart.setQuantity( currentCart.getQuantity() + 1);
        }else if(action.equals("decrease")){
            currentCart.setQuantity( currentCart.getQuantity() - 1);
        }
        cartItemRepo.save(currentCart);
    }

    private CartItem getExistCartItem(UserApp userApp, Product product) {
        CartItem cartItem = cartItemRepo.findByUserAppAndProduct(userApp, product);
        return cartItem;
    }

    private Product getProductByName(String productName){
        String slugProduct = SlugGenerator.generateSlug(productName);
        Product product = productService.findBySlug(slugProduct);
        return product;
    }
}
