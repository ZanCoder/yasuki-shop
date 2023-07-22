package com.poly.Yasuki.service;

import com.poly.Yasuki.dto.OrderDto;
import com.poly.Yasuki.entity.UserApp;

public interface OrderService {
    void create(OrderDto orderDtoList, UserApp currentUser);
}
