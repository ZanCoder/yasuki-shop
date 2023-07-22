package com.poly.Yasuki.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

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

    @JsonIgnoreProperties
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private MyCategory category;

    public Product(MyCategory category, String name,
                   BigDecimal price, Integer quantityLeft, Integer quantitySold,
                   Double percentDiscount
    ) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantityLeft = quantityLeft;
        this.quantitySold = quantitySold;
        this.percentDiscount = percentDiscount;
    }

    @Transient
    public BigDecimal getPriceDiscount(){
        BigDecimal priceDiscount = price.multiply(BigDecimal.valueOf((100 - percentDiscount)/100));
        BigDecimal result = priceDiscount.setScale(0, RoundingMode.CEILING);
//        BigDecimal result =  roundedNumber.setScale(3, RoundingMode.CEILING);
        return result;
    }
}
