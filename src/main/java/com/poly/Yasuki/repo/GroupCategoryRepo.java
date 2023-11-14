package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.MyCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupCategoryRepo extends JpaRepository<GroupCategory, Integer> {
    MyCategory findGroupCategoryBySlug(String slug);

    @Query(value = "SELECT * FROM group_categories WHERE is_active = 1 LIMIT 6", nativeQuery = true)
    List<GroupCategory> findTop6ByIsActiveTrue();
    @Query(value = "SELECT * FROM group_categories  WHERE name LIKE %:keyword%", nativeQuery = true)
    Page<GroupCategory> findByKeyword(String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM group_categories WHERE is_active = 1", nativeQuery = true)
    List<GroupCategory> getAllCategoryGroupActiveTrue();
}
