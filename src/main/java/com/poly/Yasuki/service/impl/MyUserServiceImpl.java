package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.entity.RoleApp;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.repo.MyUserRepo;
import com.poly.Yasuki.security.MyUserDetails;
import com.poly.Yasuki.service.MyUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyUserServiceImpl implements MyUserService {

    private final MyUserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp userApp = userRepo.getUserByEmail(username);
        if(userApp == null){
            throw new UsernameNotFoundException("User not found!");
        }
        return new MyUserDetails(userApp);
    }

    @Transactional
    @Override
    public UserApp create(UserApp user) {
        String pEncode = passwordEncoder.encode(user.getPassword());
        user.setPassword(pEncode);
        return userRepo.save(user);
    }

    @Override
    public UserApp findByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

    @Override
    public Page<UserApp> getUsersWithSortAndPagination(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public Page<UserApp> findByKeyword(String keyword, Pageable pageable) {
        return userRepo.findByKeyword(keyword, pageable);
    }

    @Override
    public UserApp deleteUser(Integer id) {
        Optional<UserApp> userDelete = findById(id);
        userRepo.deleteById(id);
        return userDelete.get();
    }

    @Override
    public Optional<UserApp> findById(Integer id) {
        Optional<UserApp> user =  userRepo.findById(id);
        if(user.isEmpty()){
            throw new RuntimeException("User doesn't exist!");
        }
        return user;
    }
}
