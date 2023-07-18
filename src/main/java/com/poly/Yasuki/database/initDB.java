package com.poly.Yasuki.database;

import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.service.GroupCategoryService;
import com.poly.Yasuki.service.MyCategoryService;
import com.poly.Yasuki.service.ProductService;
import com.poly.Yasuki.utils.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
@RequiredArgsConstructor
public class initDB implements CommandLineRunner {
    private final MyCategoryService categoryService;
    private final GroupCategoryService groupCategoryService;
    private final ProductService productService;
    @Override
    public void run(String... args) throws Exception {

        //init group category
        GroupCategory groupCategory1 = new GroupCategory(null, "Mỹ Phẩm", "", true, null);
        groupCategoryService.create(groupCategory1);

        GroupCategory groupCategory2 = new GroupCategory(null, "Chăm Sóc Da Mặt", "", true, null);
        groupCategoryService.create(groupCategory2);

        //init category
        MyCategory category1 = new MyCategory(  "Chăm Sóc Da Mặt Cao Cấp",groupCategory1 );
        MyCategory category2 = new MyCategory(  "Trang Điểm Cao Cấp", groupCategory1);
        MyCategory category3 = new MyCategory(  "Tẩy Trang",groupCategory2 );
        MyCategory category4 = new MyCategory("Sửa Rửa Mặt", groupCategory2);
        MyCategory category5 = new MyCategory( "Tẩy Tế Bào Chết", groupCategory2);
        categoryService.create(category1);
        categoryService.create(category2);
        categoryService.create(category3);
        categoryService.create(category4);
        categoryService.create(category5);

        //init product
        Product product0 = Product.builder()
                .name("Phấn nước").price(BigDecimal.valueOf(999))
                .category(category1)
                .percentDiscount(12.0)
                .shortDescription("Short des")
                .fullDescription("FullDes")
                .brand("SHU")
                .quantitySold(90)
                .build();
        Product product1 = new Product(category1, "Cetapil", BigDecimal.valueOf(999), 20, 20, 1.0);
        Product product2 = new Product(category1, "Lanegie", BigDecimal.valueOf(333), 3, 32, 7.0);
        Product product3 = new Product(category1, "Kiehl", BigDecimal.valueOf(777), 440, 0, 8.0);
        Product product4 = new Product(category2, "MAC", BigDecimal.valueOf(222), 44, 11, 10.0);
        productService.create(product0);
        productService.create(product1);
        productService.create(product2);
        productService.create(product3);
        productService.create(product4);
    }
}
