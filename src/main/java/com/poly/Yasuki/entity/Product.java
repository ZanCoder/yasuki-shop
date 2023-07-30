package com.poly.Yasuki.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="[products]")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String brand;

    @Column(name="price", precision = 12, scale = 3)
    private BigDecimal price;

    private Double percentDiscount;

    private Integer quantityLeft;
    private Integer quantitySold;

    @CreationTimestamp
    private Timestamp dateRelease;

    private String slug;

    @Column(length = 1024)
    private String shortDescription;

    @Column(length = 4096)
    private String fullDescription;

    @Column(length = 4096)
    private String mainImage;

    private Boolean isActive = true;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private MyCategory category;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> productImages = new ArrayList<>();

    public Product(MyCategory category, String name,
                   BigDecimal price, Integer quantityLeft, Integer quantitySold,
                   Double percentDiscount, String brand
    ) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantityLeft = quantityLeft;
        this.quantitySold = quantitySold;
        this.percentDiscount = percentDiscount;
        this.brand = brand;
    }



    @Transient
    public BigDecimal getPriceDiscount(){
        BigDecimal priceDiscount = price.multiply(BigDecimal.valueOf((100 - percentDiscount)/100));
        BigDecimal result = priceDiscount.setScale(0, RoundingMode.CEILING);
//        BigDecimal result =  roundedNumber.setScale(3, RoundingMode.CEILING);
        return result;
    }


}
