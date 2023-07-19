package com.poly.Yasuki.service;

import com.poly.Yasuki.entity.UserApp;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MyUserService extends UserDetailsService {
    UserApp create(UserApp userApp1);

    UserApp findByEmail(String email);
}
