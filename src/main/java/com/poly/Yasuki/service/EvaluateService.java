package com.poly.Yasuki.service;

import com.poly.Yasuki.dto.EvaluateDto;
import com.poly.Yasuki.entity.Evaluate;
import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.entity.RoleApp;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.enums.RoleName;
import com.poly.Yasuki.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EvaluateService {
    Evaluate create(EvaluateDto evaluateDto, UserApp currentUser);

    List<Evaluate> findByProduct(Product product);
}
