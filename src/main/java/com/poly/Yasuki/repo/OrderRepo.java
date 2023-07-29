package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.Order;
import com.poly.Yasuki.entity.RoleApp;
import com.poly.Yasuki.enums.RoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT * FROM [order]  WHERE CONCAT(name, ' ', email, ' ', phoneNumber) LIKE %:keyword%", nativeQuery = true)
    Page<Order> findByKeyword(String keyword, Pageable pageable);
}
