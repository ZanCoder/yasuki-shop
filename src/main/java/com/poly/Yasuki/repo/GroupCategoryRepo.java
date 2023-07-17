package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.MyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupCategoryRepo extends JpaRepository<GroupCategory, Integer> {
    MyCategory findGroupCategoryBySlug(String slug);

//    @Query("SELECT o FROM GroupCategory o WHERE o.isActive = true")
    List<GroupCategory> findTop6ByIsActiveTrue();
}
