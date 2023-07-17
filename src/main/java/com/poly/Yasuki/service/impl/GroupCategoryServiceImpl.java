package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.repo.GroupCategoryRepo;
import com.poly.Yasuki.service.GroupCategoryService;
import com.poly.Yasuki.utils.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupCategoryServiceImpl implements GroupCategoryService {
    private final GroupCategoryRepo groupCategoryRepo;
    @Override
    public List<GroupCategory> getAllCategoryGroupIsActive() {
        return groupCategoryRepo.findTop6ByIsActiveTrue();
    }

    @Override
    public GroupCategory create(GroupCategory groupCategory) {
        if(findGroupCategoryBySlug(groupCategory.getSlug()) != null){
            throw new RuntimeException("Category already Exists!");
        }
        String slug = SlugGenerator.generateSlug(groupCategory.getName());
        groupCategory.setSlug(slug);
        return groupCategoryRepo.save(groupCategory);
    }

    private MyCategory findGroupCategoryBySlug(String slug){
        return groupCategoryRepo.findGroupCategoryBySlug(slug);
    }
}
