package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.repo.ProductRepo;
import com.poly.Yasuki.service.ProductService;
import com.poly.Yasuki.utils.SlugGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private static final int PRODUCT_PER_TAB = 10;
    private final ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getTopSelling() {
        Page<Product> pageProducts = productRepo.getTopSelling(PageRequest.of(0,PRODUCT_PER_TAB));
        List<Product> productList = pageProducts.getContent();
        return productList;
    }

    @Override
    public List<Product> getTopDiscount() {
        Page<Product> pageProducts = productRepo.getTopDiscount( PageRequest.of(0,PRODUCT_PER_TAB));
        List<Product> productList = pageProducts.getContent();
        return productList;
    }

    @Override
    public List<Product> getTopDateRelease() {
        Page<Product> pageProducts = productRepo.getTopDateRelease(PageRequest.of(0,PRODUCT_PER_TAB));
        List<Product> productList = pageProducts.getContent();
        return productList;
    }

    @Override
    public List<Product> getListProductsByCategory(String categorySlug) {
        return productRepo.getSameProductByCategory(categorySlug, PageRequest.of(0,PRODUCT_PER_TAB));
    }

    @Override
    public Page<Product> getProductsWithSortAndPagination(Pageable pageable) {
        return productRepo.findAll(pageable);
    }


    @Override
    public Product create(Product product) {
        String slug = SlugGenerator.generateSlug(product.getName());
        if(findBySlug(slug) != null){
            throw new RuntimeException("Product already exist!");
        }
        product.setSlug(slug);
        return productRepo.save(product);
    }

    @Override
    public Product removeBySlug(String slug) {
        Product product = findBySlug(slug);
        if(product == null){
            throw new RuntimeException("Could not found product!");
        }
        productRepo.delete(product);
        return product;
    }

    @Override
    public void update(String slug, Product product) {

    }

    @Override
    public Product findBySlug(String slug) {
        return productRepo.findBySlug(slug);
    }


}
