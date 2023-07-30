package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.entity.NewsApp;
import com.poly.Yasuki.repo.NewsRepo;
import com.poly.Yasuki.service.NewsAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsAppServiceImpl implements NewsAppService {
    private final NewsRepo newsRepo;
    @Override
    public List<NewsApp> getAllNews() {
        return newsRepo.findAll();
    }

    @Override
    public NewsApp create(NewsApp news) {
        return newsRepo.save(news);
    }

    @Override
    public Page<NewsApp> findByKeyword(String keyword, Pageable pageable) {
        return newsRepo.findByKeyword(keyword, pageable);
    }

    @Override
    public Page<NewsApp> getWithSortAndPagination(Pageable pageable) {
        return newsRepo.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        newsRepo.deleteById(id);
    }

    @Override
    public Optional<NewsApp> findById(Integer id) {
        Optional<NewsApp> news = newsRepo.findById(id);
        if(news.isEmpty()){
            throw new RuntimeException("News doesn't exist!");
        }
        return news;
    }

    @Override
    public void updateStatus(Integer id, Boolean statusChanged) {

    }

    @Override
    public List<NewsApp> getTop5ByDateAndActive() {
        return newsRepo.findTop5ByDateActiveTrue();
    }
}
