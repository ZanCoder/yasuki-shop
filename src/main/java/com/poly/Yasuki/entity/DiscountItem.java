package com.poly.Yasuki.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "discount_item")
public class DiscountItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type = "Danh mục";
    @Column(name="percent_discount")
    private Double percentDiscount = 0d;

    @CreationTimestamp
    private Timestamp dateCreate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private MyCategory category;

    public DiscountItem(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }
}
