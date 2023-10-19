package com.poly.Yasuki.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poly.Yasuki.entity.GroupCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyCategoryDto {
    private Integer id;
    private String name;

    @Column(name="image",length = 1024)
    private String image;

    @Column(unique = true)
    private String slug;

    @Column(name = "is_active")
    private Boolean isActive = false;

    private Integer groupCategory;
}
