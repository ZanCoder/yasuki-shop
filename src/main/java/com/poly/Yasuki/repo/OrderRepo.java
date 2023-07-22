package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.Order;
import com.poly.Yasuki.entity.RoleApp;
import com.poly.Yasuki.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
}
