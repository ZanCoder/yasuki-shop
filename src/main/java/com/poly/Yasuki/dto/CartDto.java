package com.poly.Yasuki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Integer quantity;
    private String nameProduct;
    private BigDecimal priceProduct;
//    private String mainImageProduct;

    public BigDecimal getTotalPrice(){
        BigDecimal result = BigDecimal.valueOf(quantity).multiply(priceProduct);
        return result;
    }
}
