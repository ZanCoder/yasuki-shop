package com.poly.Yasuki.service;

import com.poly.Yasuki.entity.RoleApp;
import com.poly.Yasuki.enums.RoleName;
import com.poly.Yasuki.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public RoleApp create(RoleApp role){
        if(findRole(role.getName()) != null){
            throw new RuntimeException("Role already exist!");
        }
        return roleRepo.save(role);
    }

    public RoleApp findRole(RoleName roleName){
        return roleRepo.findByName(roleName);
    }
}
