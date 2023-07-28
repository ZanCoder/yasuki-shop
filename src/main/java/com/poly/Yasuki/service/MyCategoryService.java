package com.poly.Yasuki.service;

import com.poly.Yasuki.entity.MyCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MyCategoryService {
    List<MyCategory> getAllCategory();

    MyCategory create(MyCategory category1);

    Page<MyCategory> findByKeyword(String keyword, Pageable pageable);

    Page<MyCategory> getWithSortAndPagination(Pageable pageable);

    void deleteById(Integer id);

    Optional<MyCategory> findById(Integer id);

    void updateStatus(Integer id, Boolean statusChanged);

    List<MyCategory> findByGroupCategoryId(Integer id);
}
