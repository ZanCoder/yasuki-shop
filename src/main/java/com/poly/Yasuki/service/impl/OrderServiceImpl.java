package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.dto.OrderDto;
import com.poly.Yasuki.entity.Order;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.repo.OrderRepo;
import com.poly.Yasuki.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;

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
    }
}
