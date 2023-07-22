package com.poly.Yasuki.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String name;

    @Column(length = 1024)
    private String address;
    private String city;
    private String email;
    private String phoneNumber;
    private String note;
    private List<CartDto> cartDtoList;

}
