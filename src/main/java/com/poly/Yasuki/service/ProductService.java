package com.poly.Yasuki.service;

import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product removeBySlug(String slug);
    void update(String slug, Product product);
    Product findBySlug(String slug);
    List<Product> getAllProducts();

    List<Product> getTopSelling();

    List<Product> getTopDiscount();

    List<Product> getTopDateRelease();

    List<Product> getListProductsByCategory(String categorySlug);

    Page<Product> getProductsWithSortAndPagination(Pageable pageable);
}
