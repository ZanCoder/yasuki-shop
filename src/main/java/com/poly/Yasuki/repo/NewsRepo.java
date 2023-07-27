package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.entity.NewsApp;
import com.poly.Yasuki.entity.RoleApp;
import com.poly.Yasuki.enums.RoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository<NewsApp, Integer> {
    @Query(value = "SELECT * FROM news_app  WHERE title LIKE %:keyword%", nativeQuery = true)
    Page<NewsApp> findByKeyword(String keyword, Pageable pageable);
}
