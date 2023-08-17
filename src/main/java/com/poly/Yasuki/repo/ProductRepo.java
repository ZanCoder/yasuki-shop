package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    Product findBySlug(String slug);
    @Query(value = "SELECT * FROM products p WHERE is_active = true ORDER BY quantity_sold DESC", nativeQuery = true )
    Page<Product> getTopSelling(Pageable pageable);

    @Query(value = "SELECT * FROM products p WHERE is_active = true ORDER BY percent_discount DESC", nativeQuery = true )
    Page<Product> getTopDiscount(Pageable pageable);

    @Query(value = "SELECT * FROM products p WHERE is_active = true ORDER BY date_release DESC", nativeQuery = true )
    Page<Product> getTopDateRelease(Pageable pageable);

    @Query(value = "SELECT p FROM Product p WHERE p.category.slug = :categorySlug" )
    Page<Product> getSameProductByCategory(String categorySlug, Pageable pageable);

    @Query(value = "SELECT * FROM products  WHERE CONCAT(name, ' ',brand ) LIKE %:keyword%", nativeQuery = true)
    Page<Product> findByKeyword(String keyword, Pageable pageable);
    @Query(value = "SELECT * FROM products  WHERE CONCAT(name, ' ',brand ) LIKE %:keyword%", nativeQuery = true)
    List<Product> findByKeyword(String keyword);

    List<Product> findByCategory(MyCategory category);

    @Query(value = "SELECT p FROM Product p WHERE p.category.groupCategory = :groupCategory" )
    List<Product> findByGroup(GroupCategory groupCategory);

    @Query(value = "SELECT p FROM Product p WHERE p.isActive = true" )
    Page<Product> findByActiveTrue(Pageable pageable);

    @Query(value = "SELECT * FROM products  WHERE name LIKE %:keyword% AND is_active = 1" , nativeQuery = true)
    Page<Product> findByKeywordAndActive(String keyword, Pageable pageable);
}
