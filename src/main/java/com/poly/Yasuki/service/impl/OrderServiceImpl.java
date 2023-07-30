package com.poly.Yasuki.service.impl;

import com.poly.Yasuki.dto.CartDto;
import com.poly.Yasuki.dto.OrderDto;
import com.poly.Yasuki.entity.Order;
import com.poly.Yasuki.entity.OrderItem;
import com.poly.Yasuki.entity.Product;
import com.poly.Yasuki.entity.UserApp;
import com.poly.Yasuki.repo.OrderItemRepo;
import com.poly.Yasuki.repo.OrderRepo;
import com.poly.Yasuki.service.CartItemService;
import com.poly.Yasuki.service.OrderService;
import com.poly.Yasuki.service.ProductService;
import com.poly.Yasuki.utils.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final ProductService productService;
    private final CartItemService cartItemService;
    private final OrderItemRepo orderItemRepo;

    @Transactional
    @Override
    public void create(OrderDto orderDtoList, UserApp currentUser) {
        Order newOrder = new Order();
        if(orderDtoList.getIdOrder() != null && orderDtoList.getIdOrder() != -1){
            newOrder = orderRepo.findById(orderDtoList.getIdOrder()).get();
        }
        BeanUtils.copyProperties(orderDtoList, newOrder);
        BigDecimal totalPrice = orderDtoList.getCartDtoList().stream()
                        .map(CartDto::getTotalPrice)
                        .reduce(BigDecimal.ZERO,BigDecimal::add);
        newOrder.setUserApp(currentUser);
        newOrder.setTotalPayment(totalPrice);
        orderRepo.save(newOrder);

        orderItemRepo.deleteAllByOrder(newOrder);

        Set<OrderItem> listOrderItem = new HashSet<>();

        for (CartDto item : orderDtoList.getCartDtoList() ) {
            OrderItem orderItem = new OrderItem(item.getQuantity(),
                    item.getNameProduct(), item.getPriceProduct());
            listOrderItem.add(orderItem);
            orderItem.setOrder(newOrder);
            orderItemRepo.save(orderItem);
        }
        updateAfterOrder(orderDtoList.getCartDtoList(), currentUser);
    }
//
    @Override
    public Page<Order> findByKeyword(String keyword, Pageable pageable) {
        return orderRepo.findByKeyword(keyword, pageable);
    }

    @Override
    public Page<Order> getWithSortAndPagination(Pageable pageable) {
        return orderRepo.findAll(pageable);
    }

    @Override
    public Order insert(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public void deleteById(Integer id) {
        orderRepo.deleteById(id);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        Optional<Order> order = orderRepo.findById(id);
        if(order.isEmpty()){
            throw new RuntimeException("Order doesn't exist!");
        }
        return order;
    }


    private void updateAfterOrder(List<CartDto> cartDtoList, UserApp currentUser) {
        cartDtoList.stream().forEach(item -> {
            // update quantity product after order
            String slug = SlugGenerator.generateSlug(item.getNameProduct());
            Product product = productService.findBySlug(slug);
            product.setQuantityLeft(product.getQuantityLeft() - 1);
            product.setQuantitySold(product.getQuantitySold() + 1);

            // remove cart item  after order
            cartItemService.deleteCartItem(item.getNameProduct(), currentUser);
        });
    }
}
