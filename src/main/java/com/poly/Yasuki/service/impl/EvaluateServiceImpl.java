package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.dto.EvaluateDto;
import com.poly.Yasuki.entity.Evaluate;
import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.entity.RoleApp;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.enums.RoleName;
import com.poly.Yasuki.repo.EvaluateRepo;
import com.poly.Yasuki.repo.RoleRepo;
import com.poly.Yasuki.service.EvaluateService;
import com.poly.Yasuki.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluateServiceImpl implements EvaluateService {
    private final EvaluateRepo evaluateRepo;
    private final ProductService productService;

    @Override
    public Evaluate create(EvaluateDto evaluateDto, UserApp currentUser) {
        Evaluate newEvaluate = new Evaluate();
        newEvaluate.setContent(evaluateDto.getContent());
        newEvaluate.setNumStar(evaluateDto.getNumStar());
        newEvaluate.setProduct(productService.findById(evaluateDto.getProductId()).get());
        newEvaluate.setNameUser(currentUser.getFullName());
        return evaluateRepo.save(newEvaluate);
    }

    @Override
    public List<Evaluate> findByProduct(Product product) {
        return evaluateRepo.findAllByProduct(product);
    }
}
