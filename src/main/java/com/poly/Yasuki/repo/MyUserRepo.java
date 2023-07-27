package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.UserApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepo extends JpaRepository<UserApp, Integer> {
    UserApp getUserByEmail(String username);
    @Query(value = "SELECT * FROM users  WHERE CONCAT(email,' ', full_name) LIKE %:keyword%", nativeQuery = true)
    Page<UserApp> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
