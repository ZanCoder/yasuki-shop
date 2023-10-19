package com.poly.Yasuki.repo;

import com.poly.Yasuki.entity.Evaluate;
import com.poly.Yasuki.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluateRepo extends JpaRepository<Evaluate, Integer> {
    List<Evaluate> findAllByProduct(Product product);
}
