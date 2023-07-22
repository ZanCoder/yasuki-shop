package com.poly.Yasuki.entity;

import com.poly.Yasuki.dto.CartDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "[order]")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(length = 1024)
    private String address;
    private String email;
    private String phoneNumber;
    private String note;
    private BigDecimal totalPayment;

    @CreationTimestamp
    private Timestamp createAt;

    @Transient
    @ElementCollection
    private List<CartDto> cartDtoList;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserApp userApp;

    public Order(String name) {
        this.name = name;
    }
}
