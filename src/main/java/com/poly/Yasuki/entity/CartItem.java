package com.poly.Yasuki.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;

    @JsonIgnoreProperties
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @JsonIgnoreProperties
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserApp userApp;

//    @Transient
//    public BigDecimal getTotalPrice(){
//        BigDecimal result = BigDecimal.valueOf(quantity).multiply(product.getPrice());
//        return result;
//    }

    public CartItem( Product product, UserApp userApp) {
        this.product = product;
        this.userApp = userApp;
    }
    public CartItem( Integer quantity, Product product, UserApp userApp) {
        this.quantity = quantity;
        this.product = product;
        this.userApp = userApp;
    }
}
