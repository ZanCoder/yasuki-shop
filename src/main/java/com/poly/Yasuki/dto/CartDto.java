package com.poly.Yasuki.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    public CartDto(String nameProduct, BigDecimal priceProduct) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
    }

    @Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
