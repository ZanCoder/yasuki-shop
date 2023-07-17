package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.repo.MyCategoryRepo;
import com.poly.Yasuki.service.MyCategoryService;
import com.poly.Yasuki.utils.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyCategoryServiceImpl implements MyCategoryService {
    private final MyCategoryRepo categoryRepo;
    @Override
    public List<MyCategory> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public MyCategory create(MyCategory category1) {
        if(findCategoryBySlug(category1.getSlug()) != null){
            throw new RuntimeException("Category already Exists!");
        }
        String slug = SlugGenerator.generateSlug(category1.getName());
        category1.setSlug(slug);
        return categoryRepo.save(category1);
    }

    private MyCategory findCategoryBySlug(String slug){
        return categoryRepo.findMyCategoryBySlug(slug);
    }
}
