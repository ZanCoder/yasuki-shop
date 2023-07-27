package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.repo.GroupCategoryRepo;
import com.poly.Yasuki.service.GroupCategoryService;
import com.poly.Yasuki.utils.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Page<GroupCategory> findByKeyword(String keyword, Pageable pageable) {
        return groupCategoryRepo.findByKeyword(keyword, pageable);
    }

    @Override
    public Page<GroupCategory> getWithSortAndPagination(Pageable pageable) {
        return groupCategoryRepo.findAll(pageable);
    }

    @Override
    public GroupCategory deleteById(Integer id) {
        Optional<GroupCategory> groupCategoryDelete = findById(id);
        groupCategoryRepo.deleteById(id);
        return groupCategoryDelete.get();
    }

    @Override
    public Optional<GroupCategory> findById(Integer id) {
        Optional<GroupCategory> groupCategory = groupCategoryRepo.findById(id);
        if(groupCategory.isEmpty()){
            throw new RuntimeException("GroupCategory doesn't exist!");
        }
        return groupCategory;
    }

    @Override
    public void updateStatus(Integer id, Boolean statusChanged) {
        Optional<GroupCategory> groupCategory = groupCategoryRepo.findById(id);
        groupCategory.get().setIsActive(statusChanged);
        groupCategoryRepo.save(groupCategory.get());
    }

    @Override
    public List<GroupCategory> getAll() {
        return groupCategoryRepo.findAll();
    }

    private MyCategory findGroupCategoryBySlug(String slug){
        return groupCategoryRepo.findGroupCategoryBySlug(slug);
    }
}
