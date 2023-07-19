package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepo extends JpaRepository<UserApp, Integer> {
    UserApp getUserByEmail(String username);
}
