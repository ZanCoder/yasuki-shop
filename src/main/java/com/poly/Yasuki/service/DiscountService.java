package com.poly.Yasuki.service;

import com.poly.Yasuki.entity.DiscountItem;
import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.repo.DiscountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountRepo discountRepo;
    private final ProductService productService;
    public List<DiscountItem> getAll(){
        return discountRepo.findAll();
    }

    @Transactional
    public DiscountItem create(DiscountItem discountItem) {
        // update percent discount with product have expression
        productService.updatePercentDiscountByCategory(discountItem.getPercentDiscount(), discountItem.getCategory());
        return discountRepo.save(discountItem);
    }

    public Optional<DiscountItem> findById(Integer id) {
        Optional<DiscountItem> discountItem = discountRepo.findById(id);
        if(discountItem.isEmpty()){
            throw new RuntimeException("DiscountItem doesn't exist!");
        }
        return discountItem;
    }

    public void deleteById(Integer id) {
        Optional<DiscountItem> discountItem = discountRepo.findById(id);
        if(discountItem.isEmpty()) return;
        productService.updatePercentDiscountByCategory(0d, discountItem.get().getCategory());
        discountRepo.deleteById(id);
    }
}
