package com.poly.Yasuki.dto;

import com.poly.Yasuki.entity.MyCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountItemDto {
    private Integer id;

    private String type = "Danh mục";
    @Column(name="percent_discount")
    private Double percentDiscount;

    private String dateCreateString;

    private Integer category;
}
