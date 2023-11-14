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
import java.util.*;


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

    @Column(name="percent_discount")
    private Double percentDiscount = 0d;

    @Column(name="quantity_left")
    private Integer quantityLeft;

    @Column(name="quantity_sold")
    private Integer quantitySold;

    @Column(name="date_release")
    @CreationTimestamp
    private Timestamp dateRelease;

    private String slug;

    @Column(name="short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name="full_description", columnDefinition = "TEXT")
    private String fullDescription;

    @Column(name="main_image",length = 1024)
    private String mainImage;

    @Column(name = "is_active")
    private Boolean isActive = true;

    //data compare
    @JsonIgnore
    @ElementCollection
    @Column(name = "expression_compare")
    private List<String> expressionCompare = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private MyCategory category;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> productImages = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Evaluate> evaluates = new ArrayList<>();

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

    @Transient
    public Integer getAvgStar(){
        if(evaluates.size() == 0 ){
            return 5; // 5 star
        }
        OptionalDouble avgStar = evaluates.stream().mapToInt(Evaluate::getNumStar).average();
        return  (int) Math.ceil(avgStar.getAsDouble());
    }


}
