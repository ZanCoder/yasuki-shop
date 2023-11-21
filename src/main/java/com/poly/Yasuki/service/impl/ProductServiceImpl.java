package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.entity.ProductImage;
import com.poly.Yasuki.repo.MyCategoryRepo;
import com.poly.Yasuki.repo.ProductImageRepo;
import com.poly.Yasuki.repo.ProductRepo;
import com.poly.Yasuki.service.GroupCategoryService;
import com.poly.Yasuki.service.MyCategoryService;
import com.poly.Yasuki.service.ProductService;
import com.poly.Yasuki.utils.SlugGenerator;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private static final int PRODUCT_PER_TAB = 10;
    private final ProductRepo productRepo;
    private final GroupCategoryService groupCategoryService;
    private final MyCategoryService categoryService;
    private final ProductImageRepo productImageRepo;

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
    public Page<Product> getListProductsByCategory(String categorySlug, Pageable pageable) {
        return productRepo.getSameProductByCategory(categorySlug, pageable);
    }

    @Override
    public Page<Product> getProductsWithSortAndPagination(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    public Page<Product> getAllAndActiveTrue(Pageable pageable) {
        return productRepo.findByActiveTrue(pageable);
    }

    @Override
    public Page<Product> findByKeywordAndActive(String keyword, Pageable pageable) {
        return productRepo.findByKeywordAndActive(keyword, pageable);
    }



    @Override
    public void updateStatus(Integer id, Boolean statusChanged) {
        Optional<Product> product = productRepo.findById(id);
        product.get().setIsActive(statusChanged);
        productRepo.save(product.get());
    }

    @Override
    public Optional<Product> findById(Integer id) {
        Optional<Product> product = productRepo.findById(id);
        if(product.isEmpty()){
            throw new RuntimeException("Product doesn't exist!");
        }
        return product;
    }

    @Override
    public void deleteById(Integer id) {
        productRepo.deleteById(id);
    }

    @Override
    public Page<Product> findByKeyword(String keyword, Pageable pageable) {
        return productRepo.findByKeyword(keyword, pageable);
    }
    @Override
    public List<Product> findByKeyword(String keyword) {
        return productRepo.findByKeyword(keyword);
    }

    @Override
    public Integer getCurrentIndexForGC(Product product) {
        MyCategory category = product.getCategory();
        GroupCategory groupCategory = category.getGroupCategory();
        List<GroupCategory> groupCategoryList = groupCategoryService.getAll();
        return groupCategoryList.indexOf(groupCategory);
    }


    @Override
    public Product create(Product product) {
        String slug = SlugGenerator.generateSlug(product.getName());
        if(findBySlug(slug) != null){
            throw new RuntimeException("Product already exist!");
        }
        product.setSlug(slug);
        Product productSaved = productRepo.save(product);
        if(productSaved.getProductImages() != null){
            productSaved.getProductImages().forEach(productImage -> {
                productImage.setProduct(productSaved);
                productImageRepo.save(productImage);
            });
        }
        return productSaved;

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


    @Transactional
    @Override
    public void update(Integer id, Product product) {
        Optional<Product> oldProductOptional = findById(id);
        if (oldProductOptional.isEmpty()) {
            throw new RuntimeException("Could not find product!");
        }

        Product oldProduct = oldProductOptional.get();

        // Lấy danh sách hình ảnh cũ
        List<ProductImage> oldProductImages = oldProduct.getProductImages();

        // Xóa hình ảnh cũ
        if (oldProductImages != null && !oldProductImages.isEmpty()) {
            oldProductImages.forEach(productImage -> productImageRepo.deleteById(productImage.getId()));
            oldProduct.getProductImages().clear();
        }

        // Sao chép thuộc tính từ product mới vào product cũ (tránh việc ghi đè ngày phát hành và các thuộc tính khác)
        BeanUtils.copyProperties(product, oldProduct, "id", "productImages");

        // Cập nhật ngày phát hành và tạo slug
        Timestamp dateRelease = oldProduct.getDateRelease();
        oldProduct.setDateRelease(dateRelease);
        String slug = SlugGenerator.generateSlug(oldProduct.getName());
        oldProduct.setSlug(slug);

        // Lưu lại thông tin cập nhật của sản phẩm
        Product updatedProduct = productRepo.save(oldProduct);

        // Cập nhật danh sách hình ảnh cho sản phẩm
        if (product.getProductImages() != null && !product.getProductImages().isEmpty()) {
            product.getProductImages().forEach(productImage -> {
                productImage.setProduct(updatedProduct);
                productImageRepo.save(productImage);
            });
        }
    }

    @Override
    public Product findBySlug(String slug) {
        return productRepo.findBySlug(slug);
    }

    @Override
    public List<Product> getListProductsByGroupId(Integer id) {
        GroupCategory groupCategory = groupCategoryService.findById(id).get();
        return productRepo.findByGroup(groupCategory);
    }
    @Override
    public Page<Product> getListProductsByNameGroupCategory(String groupCategory, Pageable pageable) {
        String slug = SlugGenerator.generateSlug(groupCategory);
        return productRepo.findBySlugGroupCategoryAndPagination(slug, pageable);
    }

    @Override
    public List<String> getAllBrand() {
        return productRepo.getListBrand();
    }

    @Override
    public void updatePercentDiscountByCategory(Double percentDiscount, MyCategory category) {
        productRepo.updatePercentDiscountByCategory(percentDiscount, category);
    }

    @Override
    public List<Product> getListProductsByCategoryId(Integer id) {
        MyCategory category = categoryService.findById(id).get();
        return productRepo.findByCategory(category);
    }


}
