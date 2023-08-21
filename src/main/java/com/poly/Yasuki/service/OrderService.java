package com.poly.Yasuki.service;

import com.poly.Yasuki.dto.OrderDto;
import com.poly.Yasuki.entity.Order;
import com.poly.Yasuki.entity.UserApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void create(OrderDto orderDtoList, UserApp currentUser);

    Page<Order> findByKeyword(String keyword, Pageable pageable);

    Page<Order> getWithSortAndPagination(Pageable pageable);

    Order insert(Order order);

    void deleteById(Integer id);

    Optional<Order> findById(Integer id);
    List<Order> findByUser(UserApp currentUser);

    List<Order> findByUserAndStatus(UserApp currentUser, String status);
}
