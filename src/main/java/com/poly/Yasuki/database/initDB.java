package com.poly.Yasuki.database;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.entity.*;
import com.poly.Yasuki.enums.RoleName;
import com.poly.Yasuki.service.*;
import com.poly.Yasuki.utils.SlugGenerator;
import lombok.Builder;
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
    private final OrderService orderService;
    private final NewsAppService newsAppService;
    @Override
    public void run(String... args) throws Exception {

        //init group category
        GroupCategory groupCategory1 = new GroupCategory(null, "Mỹ Phẩm", "", false, null);
        GroupCategory groupCategory2 = new GroupCategory(null, "Chăm Sóc Da Mặt", "", true, null);
        GroupCategory groupCategory3 = new GroupCategory(null, "Chăm Sóc Cơ thể", "", true, null);
        GroupCategory groupCategory4 = new GroupCategory(null, "Chăm Sóc Cá Nhân", "", true, null);
        GroupCategory groupCategory5 = new GroupCategory(null, "Nước Hoa", "", true, null);
        GroupCategory groupCategory6 = new GroupCategory(null, "Giảm Béo", "", false, null);
        groupCategoryService.create(groupCategory1);
        groupCategoryService.create(groupCategory2);
        groupCategoryService.create(groupCategory3);
        groupCategoryService.create(groupCategory4);
        groupCategoryService.create(groupCategory5);
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
                .quantitySold(90).quantityLeft(10)
                .build();
        Product product1 = new Product(category1, "Cetapil", BigDecimal.valueOf(1998979), 20, 20, 1.0, "C");
        Product product2 = new Product(category1, "Lanegie", BigDecimal.valueOf(3387983), 3, 32, 7.0, "C");
        Product product3 = new Product(category1, "Kiehl", BigDecimal.valueOf(899777), 440, 0, 8.0, "C");
        Product product4 = new Product(category2, "MAC", BigDecimal.valueOf(222676), 44, 11, 10.0, "C");
        Product product5 = new Product(category3, "Nước Tẩy Trang", BigDecimal.valueOf(1998979), 20, 20, 1.0, "C");
        Product product6 = new Product(category3, "Bông Tẩy Trang", BigDecimal.valueOf(3387983), 3, 32, 7.0, "C");
        Product product7 = new Product(category4, "Sữa Rửa Mặt Cetaphil Dịu Lành Cho Da Nhạy Cảm 500ml", BigDecimal.valueOf(899777), 440, 0, 8.0, "C");
        Product product8 = new Product(category5, "Túi Refill Tẩy Tế Bào Chết Toàn Thân", BigDecimal.valueOf(222676), 44, 11, 10.0, "C");
        Product product9 = new Product(category9, "Ban Chai danh rangg", BigDecimal.valueOf(222676), 44, 11, 10.0, "C");
        product1.setMainImage("https://media.hasaki.vn/catalog/product/p/r/promotions-auto-sua-rua-mat-cetaphil-diu-nhe-khong-xa-phong-125ml-moi_A9AYTmiVPjkRo29X_img_358x358_843626_fit_center.png");
        product2.setMainImage("https://media.hasaki.vn/catalog/product/g/o/google-shopping-mat-na-ngu-moi-laneige-huong-qua-mong-mini-8g-1_img_358x358_843626_fit_center.jpg");
        product3.setMainImage("https://media.hasaki.vn/catalog/product/g/o/google-shopping-mat-na-kiehl-s-nghe-hat-viet-quat-lam-sang-da-28ml-1686566767_img_358x358_843626_fit_center.jpg");
        product0.setMainImage("https://media.hasaki.vn/catalog/product/p/h/phan-nuoc-gilaa-kiem-dau-va-duong-da-2-natural-beige-13g-10_img_358x358_843626_fit_center.jpg");
        product5.setMainImage("https://media.hasaki.vn/catalog/product/p/r/promotions-auto-nuoc-tay-trang-tuoi-mat-l-oreal-3-in-1-danh-cho-da-dau-da-hon-hop-400ml_hT9R6HqHaZS1omAq_img_358x358_843626_fit_center.png");
        product6.setMainImage("https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-bo-3-hop-bong-tay-trang-co-ban-silcot-82-mieng-hop-1684396744_img_358x358_843626_fit_center.jpg");
        product4.setMainImage("https://media.hasaki.vn/catalog/product/t/o/top_fb_ads_100550094_310523-1685529668_img_358x358_843626_fit_center.jpg");
        product7.setMainImage("");
        product8.setMainImage("https://media.hasaki.vn/catalog/product/f/a/facebook-dynamic-422208882-1689915355_img_358x358_843626_fit_center.jpg");
        product9.setMainImage("https://media.hasaki.vn/catalog/product/p/r/promotions-auto-bo-2-ban-chai-danh-rang-colgate-long-chai-khang-khuan_vZEzJ2ih9fWzpgut_img_358x358_843626_fit_center.png");

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
        CartDto cartDto1 = new CartDto(1,"Cetapil", BigDecimal.valueOf(1998979), "" );
        CartDto cartDto2 = new CartDto(1, "MAC", BigDecimal.valueOf(222676),"");
        CartDto cartDto3 = new CartDto(1, "Kiehl", BigDecimal.valueOf(899777),"");
        cartItemService.addToCart(cartDto1, userApp2);
        cartItemService.addToCart(cartDto2, userApp2);
        cartItemService.addToCart(cartDto3, userApp2);
        cartItemService.addToCart(cartDto3, userApp2);
        cartItemService.addToCart(cartDto3, userApp1);

        //init order
        Order order1 = new Order("Tuan Kiet", "799 Quanrg Tring 79234 Go vap ", "rtuankier@gmail.com", "093404566",BigDecimal.valueOf(1995468979), "Đặt hàng");
        Order order2 = new Order("Tuan Kiet", "799 Quanrg Tring 79234 Go vap ", "rtuankier@gmail.com", "093404566",BigDecimal.valueOf(1995468979), "Đặt hàng");
        Order order3 = new Order("Tuan Kiet", "799 Quanrg Tring 79234 Go vap ", "rtuankier@gmail.com", "093404566",BigDecimal.valueOf(1995468979), "Đặt hàng");
        orderService.insert(order1);
        orderService.insert(order2);
        orderService.insert(order3);

        //news init
        NewsApp newsApp1 = NewsApp.builder()
                .title("Những Item makeup nhà Etude House giá hạt dẻ")
                .image("https://www.kosmebox.com/image/cache/data/BLOG/Nhung-item-makeup-nha-etude-house-gia-hat-de/Nhung-item-makeup-nha-etude-house-gia-hat-de-7-9-225x225.jpg")
                .content("House giá hạt dẻ, chất miễn đùa Không phải là những item makeup mới. Thậm chí nếu không nói là lâu đời. Nhưng ở thời điểm hiện tại")
                .build();

        NewsApp newsApp2 = NewsApp.builder()
                .title("Những Item makeup nhà Etude House giá hạt dẻ")
                .image("https://www.kosmebox.com/image/cache/data/BLOG/Nhung-item-makeup-nha-etude-house-gia-hat-de/Nhung-item-makeup-nha-etude-house-gia-hat-de-7-9-225x225.jpg")
                .content("House giá hạt dẻ, chất miễn đùa Không phải là những item makeup mới. Thậm chí nếu không nói là lâu đời. Nhưng ở thời điểm hiện tại")
                .build();

        NewsApp newsApp3 = NewsApp.builder()
                .title("Những Item makeup nhà Etude House giá hạt dẻ")
                .image("https://www.kosmebox.com/image/cache/data/BLOG/Nhung-item-makeup-nha-etude-house-gia-hat-de/Nhung-item-makeup-nha-etude-house-gia-hat-de-7-9-225x225.jpg")
                .content("House giá hạt dẻ, chất miễn đùa Không phải là những item makeup mới. Thậm chí nếu không nói là lâu đời. Nhưng ở thời điểm hiện tại")
                .build();

        newsAppService.create(newsApp1);
        newsAppService.create(newsApp2);
        newsAppService.create(newsApp3);
    }
}
