package com.poly.Yasuki.controller.admin_controller;

import com.poly.Yasuki.entity.NewsApp;
import com.poly.Yasuki.service.NewsAppService;
import com.poly.Yasuki.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ManagerNewsController {
    private final NewsAppService newsAppService;
    private final int ITEM_PER_PAGE = 10;
    @GetMapping("/admin/manager-news")
    public String  viewManagerNewsPage(
                @RequestParam(name="page", defaultValue = "1", required = false)  int page,
                @RequestParam(name="sortBy",defaultValue = "id", required = false) String sortBy,
                @RequestParam(name="orderBy", defaultValue = "asc",  required = false) String orderBy,
                @RequestParam(name="keyword",  required = false) String keyword,
                Model model){
        Pageable pageable = PageRequest.of(page -1, ITEM_PER_PAGE)
                        .withSort(Sort.by(Sort.Direction.fromString(orderBy), sortBy));
        Page<NewsApp> liNewsApps = null;
        if(keyword != null){
            liNewsApps = newsAppService.findByKeyword(keyword, pageable);
            model.addAttribute("keyword", keyword);
        }else{
            liNewsApps = newsAppService.getWithSortAndPagination(pageable);
        }
        model.addAttribute("listNews", liNewsApps.getContent());
        model.addAttribute("totalPages", liNewsApps.getTotalPages());
        model.addAttribute("totalElements", liNewsApps.getTotalElements());
        model.addAttribute("currentPage", page);
        model.addAttribute("addNews", new NewsApp());
        return "admin/manager_news";
    }
    @PostMapping("/admin/manager-news/add")
    public String doCreateNews(@ModelAttribute(name = "addNews") NewsApp newsApp,
                                  Model model){
        try{
            if(newsApp.getId() != null){
                Optional<NewsApp> oldNews = newsAppService.findById(newsApp.getId());
                newsApp.setCreateAt(oldNews.get().getCreateAt());
            }
            newsAppService.create(newsApp);
            model.addAttribute("success", MessageUtils.ADD_SUCCESS);
        }catch(Exception ex){
            model.addAttribute("error", MessageUtils.ADD_FAILED);
        }
        return "redirect:/admin/manager-news";
    }

    @DeleteMapping("/admin/manager-news/delete")
    @ResponseBody
    public ResponseEntity<?> doDeleteNews(@RequestParam(name = "id") Integer id){
        try{
            newsAppService.deleteById(id);
        }catch(Exception e){
            return ResponseEntity.status(500).body(MessageUtils.ERROR_FOREIGN_KEY);
        }
        return ResponseEntity.status(204).body("DELETED");
    }

    @GetMapping("/admin/manager-news/edit")
    @ResponseBody
    public NewsApp editCategories(@RequestParam(name = "id") Integer id,
            Model model){
        model.addAttribute("mode", "edit");
        return newsAppService.findById(id).get();
    }

    @GetMapping("/admin/manager-news/change-status")
    @ResponseBody
    public ResponseEntity<?> changeStatusCategories(
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "statusChanged") Boolean statusChanged){
        newsAppService.updateStatus(id, statusChanged);
        return ResponseEntity.status(200).body("UPDATED");
    }

}
