package com.poly.Yasuki.database;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.entity.*;
import com.poly.Yasuki.enums.RoleName;
import com.poly.Yasuki.service.*;
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
    private final RoleService roleService;
    private final MyUserService userService;
    private final CartItemService cartItemService;
    @Override
    public void run(String... args) throws Exception {

        //init group category
        GroupCategory groupCategory1 = new GroupCategory(null, "Mỹ Phẩm", "", false, null);
        groupCategoryService.create(groupCategory1);

        GroupCategory groupCategory2 = new GroupCategory(null, "Chăm Sóc Da Mặt", "", true, null);
        groupCategoryService.create(groupCategory2);
        GroupCategory groupCategory3 = new GroupCategory(null, "Chăm Sóc Cơ thể", "", true, null);
        groupCategoryService.create(groupCategory3);
        GroupCategory groupCategory4 = new GroupCategory(null, "Chăm Sóc Cá Nhân", "", true, null);
        groupCategoryService.create(groupCategory4);
        GroupCategory groupCategory5 = new GroupCategory(null, "Nước Hoa", "", true, null);
        groupCategoryService.create(groupCategory5);
        GroupCategory groupCategory6 = new GroupCategory(null, "Giảm Béo", "", false, null);
        groupCategoryService.create(groupCategory6);

        //init category
        MyCategory category1 = new MyCategory(  "Chăm Sóc Da Mặt Cao Cấp",groupCategory1 );
        MyCategory category2 = new MyCategory(  "Trang Điểm Cao Cấp", groupCategory1);
        MyCategory category3 = new MyCategory(  "Tẩy Trang",groupCategory2 );
        MyCategory category4 = new MyCategory("Sửa Rửa Mặt", groupCategory2);
        MyCategory category5 = new MyCategory( "Tẩy Tế Bào Chết", groupCategory2);

        MyCategory category6 = new MyCategory(  "Khẩu trang",groupCategory3 );
        MyCategory category7 = new MyCategory("Mặt Nạ Xông Hơi", groupCategory3);
        MyCategory category8 = new MyCategory( "Chống Muỗi", groupCategory3);

        MyCategory category9 = new MyCategory( "Bàn Chải Đánh Răng", groupCategory4);


        categoryService.create(category1);
        categoryService.create(category2);
        categoryService.create(category3);
        categoryService.create(category4);
        categoryService.create(category5);
        categoryService.create(category6);
        categoryService.create(category7);
        categoryService.create(category8);
        categoryService.create(category9);

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
        Product product1 = new Product(category1, "Cetapil", BigDecimal.valueOf(1998979), 20, 20, 1.0);
        Product product2 = new Product(category1, "Lanegie", BigDecimal.valueOf(3387983), 3, 32, 7.0);
        Product product3 = new Product(category1, "Kiehl", BigDecimal.valueOf(899777), 440, 0, 8.0);
        Product product4 = new Product(category2, "MAC", BigDecimal.valueOf(222676), 44, 11, 10.0);
        Product product5 = new Product(category3, "Nước Tẩy Trang", BigDecimal.valueOf(1998979), 20, 20, 1.0);
        Product product6 = new Product(category3, "Bông Tẩy Trang", BigDecimal.valueOf(3387983), 3, 32, 7.0);
        Product product7 = new Product(category4, "Sữa Rửa Mặt Cetaphil Dịu Lành Cho Da Nhạy Cảm 500ml", BigDecimal.valueOf(899777), 440, 0, 8.0);
        Product product8 = new Product(category5, "Túi Refill Tẩy Tế Bào Chết Toàn Thân", BigDecimal.valueOf(222676), 44, 11, 10.0);
        Product product9 = new Product(category9, "Ban Chai danh rangg", BigDecimal.valueOf(222676), 44, 11, 10.0);

        productService.create(product0);
        productService.create(product1);
        productService.create(product2);
        productService.create(product3);
        productService.create(product4);
        productService.create(product5);
        productService.create(product6);
        productService.create(product7);
        productService.create(product8);
        productService.create(product9);

        //        init role
        RoleApp roleApp1 = new RoleApp(null, RoleName.ROLE_ADMIN);
        RoleApp roleApp2 = new RoleApp(null, RoleName.ROLE_USER);
        roleService.create(roleApp1);
        roleService.create(roleApp2);

        //        init user
        Set<RoleApp> roles1 = new HashSet<>();
        roles1.add(roleApp1);
        UserApp userApp1 = new UserApp(null,"tk", "123", "Tuan Kiet", true, roles1);

        Set<RoleApp> roles2 = new HashSet<>();
        roles2.add(roleApp2);
        UserApp userApp2 = new UserApp(null,"dc", "123", "Duc Cong", true, roles2);

        userService.create(userApp1);
        userService.create(userApp2);

        //init cart
        CartDto cartDto1 = new CartDto(1,"Cetapil", BigDecimal.valueOf(1998979) );
        CartDto cartDto2 = new CartDto(1, "MAC", BigDecimal.valueOf(222676));
        CartDto cartDto3 = new CartDto(1, "Kiehl", BigDecimal.valueOf(899777));
        cartItemService.addToCart(cartDto1, userApp2);
        cartItemService.addToCart(cartDto2, userApp2);
        cartItemService.addToCart(cartDto3, userApp2);
        cartItemService.addToCart(cartDto3, userApp2);
        cartItemService.addToCart(cartDto3, userApp1);
    }
}
