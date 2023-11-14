package com.poly.Yasuki.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private Integer productId;
    private Integer quantity;
    private String name; // name product
    private BigDecimal price;
}
