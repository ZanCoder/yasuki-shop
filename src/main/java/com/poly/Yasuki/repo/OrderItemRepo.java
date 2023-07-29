package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
    void deleteAllByOrder(Order newOrder);
}
