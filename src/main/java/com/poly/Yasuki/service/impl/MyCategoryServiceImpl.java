package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.repo.GroupCategoryRepo;
import com.poly.Yasuki.repo.MyCategoryRepo;
import com.poly.Yasuki.service.MyCategoryService;
import com.poly.Yasuki.utils.SlugGenerator;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyCategoryServiceImpl implements MyCategoryService {
    private final MyCategoryRepo categoryRepo;
    private final GroupCategoryRepo groupCategoryRepo;
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

    @Override
    public Page<MyCategory> findByKeyword(String keyword, Pageable pageable) {
        return categoryRepo.findByKeyword(keyword, pageable);
    }

    @Override
    public Page<MyCategory> getWithSortAndPagination(Pageable pageable) {
        return categoryRepo.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
//        Optional<MyCategory> categoryDelete = findById(id);
        categoryRepo.deleteById(id);
    }

    @Override
    public Optional<MyCategory> findById(Integer id) {
        Optional<MyCategory> category = categoryRepo.findById(id);
        if(category.isEmpty()){
            throw new RuntimeException("Category doesn't exist!");
        }
        return category;
    }

    @Override
    public void updateStatus(Integer id, Boolean statusChanged) {
        Optional<MyCategory> category = categoryRepo.findById(id);
        category.get().setIsActive(statusChanged);
        categoryRepo.save(category.get());
    }

    @Override
    public List<MyCategory> findByGroupCategoryId(Integer id) {
        GroupCategory groupCategory = groupCategoryRepo.findById(id).get();
        return categoryRepo.findByGroupCategory(groupCategory);
    }

    private MyCategory findCategoryBySlug(String slug){
        return categoryRepo.findMyCategoryBySlug(slug);
    }
}
