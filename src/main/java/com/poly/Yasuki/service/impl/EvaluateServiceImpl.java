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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Page<Evaluate> findByKeyword(String keyword, Integer productId, Pageable pageable) {
        Optional<Product> product = productService.findById(productId);
        if(product.isEmpty()) throw new RuntimeException("Product doe not exist!");
        return evaluateRepo.findByKeyword(keyword, product.get(), pageable);
    }

    @Override
    public Page<Evaluate> getCommentsWithPagination(Integer productId, Pageable pageable) {
        Optional<Product> product = productService.findById(productId);
        if(product.isEmpty()) throw new RuntimeException("Product doe not exist!");
        return evaluateRepo.findAllByProduct(product.get(), pageable);
    }
    @Override
    public void deleteById(Integer id) {
        evaluateRepo.deleteById(id);
    }
}
