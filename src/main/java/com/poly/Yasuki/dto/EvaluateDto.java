package com.poly.Yasuki.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateDto {
    private Integer numStar;

    @Column(length = 1024)
    private String content;

    private Integer productId;

}
