package com.poly.Yasuki.service;

import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.entity.NewsApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface NewsAppService {
    List<NewsApp> getAllNews();

    NewsApp create(NewsApp news);

    Page<NewsApp> findByKeyword(String keyword, Pageable pageable);

    Page<NewsApp> getWithSortAndPagination(Pageable pageable);

    void deleteById(Integer id);

    Optional<NewsApp> findById(Integer id);

    void updateStatus(Integer id, Boolean statusChanged);
}
