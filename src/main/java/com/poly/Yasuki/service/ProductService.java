package com.poly.Yasuki.service;

import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product create(Product product);
    Product removeBySlug(String slug);
    void update(Integer id, Product product);
    Product findBySlug(String slug);
    List<Product> getAllProducts();

    List<Product> getTopSelling();

    List<Product> getTopDiscount();

    List<Product> getTopDateRelease();

    Page<Product> getListProductsByCategory(String categorySlug, Pageable pageable);

    Page<Product> getProductsWithSortAndPagination(Pageable pageable);

    void updateStatus(Integer id, Boolean statusChanged);

    Optional<Product> findById(Integer id);

    void deleteById(Integer id);

    Page<Product> findByKeyword(String keyword, Pageable pageable);
    List<Product> findByKeyword(String keyword);

    Integer getCurrentIndexForGC(Product product);

    List<Product> getListProductsByGroupId(Integer id);

    List<Product> getListProductsByCategoryId(Integer id);

    Page<Product> getAllAndActiveTrue(Pageable pageable);

    Page<Product> findByKeywordAndActive(String keyword, Pageable pageable);

    Page<Product> getListProductsByNameGroupCategory(String groupCategory, Pageable pageable);
}
