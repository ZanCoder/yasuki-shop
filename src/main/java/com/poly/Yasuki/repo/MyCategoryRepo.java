package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.MyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCategoryRepo extends JpaRepository<MyCategory, Integer> {
    MyCategory findMyCategoryBySlug(String slug);
}
