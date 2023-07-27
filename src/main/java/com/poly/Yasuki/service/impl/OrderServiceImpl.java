package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.dto.OrderDto;
import com.poly.Yasuki.entity.Order;
import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.repo.OrderRepo;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.OrderService;
import com.poly.Yasuki.service.ProductService;
import com.poly.Yasuki.utils.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final ProductService productService;
    private final CartItemService cartItemService;

    @Override
    public void create(OrderDto orderDtoList, UserApp currentUser) {
        Order newOrder = new Order();
        BeanUtils.copyProperties(orderDtoList, newOrder);
        BigDecimal totalPrice = orderDtoList.getCartDtoList().stream()
                        .map(CartDto::getTotalPrice)
                        .reduce(BigDecimal.ZERO,BigDecimal::add);
        newOrder.setUserApp(currentUser);
        newOrder.setTotalPayment(totalPrice);
        orderRepo.save(newOrder);
        updateAfterOrder(orderDtoList.getCartDtoList(), currentUser);
    }

    private void updateAfterOrder(List<CartDto> cartDtoList, UserApp currentUser) {
        cartDtoList.stream().forEach(item -> {
            // update quantity product after order
            String slug = SlugGenerator.generateSlug(item.getNameProduct());
            Product product = productService.findBySlug(slug);
            product.setQuantityLeft(product.getQuantityLeft() - 1);
            product.setQuantitySold(product.getQuantitySold() + 1);

            // remove cart item  after order
            cartItemService.deleteCartItem(item.getNameProduct(), currentUser);
        });
    }
}
