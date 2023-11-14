package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.Evaluate;
import com.poly.Yasuki.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluateRepo extends JpaRepository<Evaluate, Integer> {
    List<Evaluate> findAllByProduct(Product product);
    @Query(value = "SELECT o FROM Evaluate o WHERE CONCAT(o.nameUser, '', o.content) LIKE %?1% AND o.product = ?2")
    Page<Evaluate> findByKeyword(String keyword, Product product, Pageable pageable);

    Page<Evaluate> findAllByProduct(Product product, Pageable pageable);
}
