package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.repo.MyUserRepo;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.MyUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyUserServiceImpl implements MyUserService {

    private final MyUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp userApp = userRepo.getUserByEmail(username);
        if(userApp == null){
            throw new UsernameNotFoundException("User not found!");
        }
        return new MyUserDetails(userApp);
    }

    @Override
    public UserApp create(UserApp user) {
        if(findByEmail(user.getEmail()) != null){
            throw new RuntimeException("User already exist!");
        }
        String pEncode = passwordEncoder.encode(user.getPassword());
        user.setPassword(pEncode);
        return userRepo.save(user);
    }

    @Override
    public UserApp findByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }
}
