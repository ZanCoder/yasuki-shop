package com.poly.Yasuki.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.Yasuki.dto.CartDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "[evaluate]")
@Builder
public class Evaluate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numStar;

    @Column(length = 1024)
    private String content;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")

    private String nameUser;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public Evaluate(Integer numStar, String content, Product product) {
        this.numStar = numStar;
        this.content = content;
        this.product = product;
    }

    //    @Override
//    public String toString() {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.writeValueAsString(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "{}";
//        }
//    }

}
