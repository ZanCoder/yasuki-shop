package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.DiscountItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepo extends JpaRepository<DiscountItem, Integer> {
}
