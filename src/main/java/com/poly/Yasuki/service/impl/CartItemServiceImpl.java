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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepo cartItemRepo;
    private final ProductService productService;

    @Override
    public void addToCart(Integer quantity, Integer productId, UserApp userApp) {
        Product product = productService.findById(productId).get();
        CartItem cartItem = new CartItem(quantity,  product, userApp);
        // cart is exist | + 1
        CartItem oldCart = getExistCartItem(userApp, product);
        if(oldCart != null){
            Integer newQuantity = oldCart.getQuantity() + quantity;
            oldCart.setQuantity(newQuantity);
            cartItemRepo.save(oldCart);
        }else{ // new cart item
            cartItemRepo.save(cartItem);
        }
    }

    @Override
    public List<CartItem> getCartsByUser(UserApp currentUser) {
        List<CartItem> cartItemList = cartItemRepo.findByUserApp(currentUser);
        return cartItemList;
    }


    @Override
    public void deleteCartItem(Integer productId, UserApp currentUser) {
        Optional<Product> product = productService.findById(productId);
        cartItemRepo.deleteByProductAndUserApp(product.get(), currentUser);
    }

    @Override
    public void updateCartItem(String action, Integer productId, UserApp currentUser) {
        Optional<Product> product = productService.findById(productId);
        CartItem currentCart =  cartItemRepo.findByUserAppAndProduct(currentUser, product.orElse(null));
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
    @Override
    public int getSize(UserApp userApp) {
        List<CartItem>  cartItemList = getCartsByUser(userApp);
        return cartItemList.size();
    }
}
