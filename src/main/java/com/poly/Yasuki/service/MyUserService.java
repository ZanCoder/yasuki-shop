package com.poly.Yasuki.service;

import com.poly.Yasuki.entity.RoleApp;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.enums.RoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface MyUserService extends UserDetailsService {
    UserApp create(UserApp userApp1);

    UserApp findByEmail(String email);

    Page<UserApp> getUsersWithSortAndPagination(Pageable pageable);

    Page<UserApp> findByKeyword(String keyword, Pageable pageable);

    UserApp deleteUser(Integer id);

    Optional<UserApp> findById(Integer id);
}
