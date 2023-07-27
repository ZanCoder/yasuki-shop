package com.poly.Yasuki.service;

import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.MyCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GroupCategoryService {
    List<GroupCategory> getAllCategoryGroupIsActive();

    GroupCategory create(GroupCategory groupCategory);

    Page<GroupCategory> findByKeyword(String keyword, Pageable pageable);

    Page<GroupCategory> getWithSortAndPagination(Pageable pageable);

    GroupCategory deleteById(Integer id);

    Optional<GroupCategory> findById(Integer id);

    void updateStatus(Integer id, Boolean currentStatus);

    List<GroupCategory> getAll();
}
