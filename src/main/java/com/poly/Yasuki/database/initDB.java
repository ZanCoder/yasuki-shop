package com.poly.Yasuki.database;

import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.entity.MyCategory;
import com.poly.Yasuki.service.GroupCategoryService;
import com.poly.Yasuki.service.MyCategoryService;
import com.poly.Yasuki.utils.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class initDB implements CommandLineRunner {
    private final MyCategoryService categoryService;
    private final GroupCategoryService groupCategoryService;
    @Override
    public void run(String... args) throws Exception {


        GroupCategory groupCategory1 = new GroupCategory(null, "Mỹ Phẩm", "", true, null);
        groupCategoryService.create(groupCategory1);

        GroupCategory groupCategory2 = new GroupCategory(null, "Chăm Sóc Da Mặt", "", true, null);
        groupCategoryService.create(groupCategory2);

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
    }
}
