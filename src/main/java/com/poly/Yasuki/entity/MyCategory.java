package com.poly.Yasuki.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "my_categories")
public class MyCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(name="image",length = 1024)
    private String image;

    @Column(unique = true)
    private String slug;

    @Column(name = "is_active")
    private Boolean isActive = false;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private GroupCategory groupCategory;


    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public MyCategory( String name,GroupCategory groupCategory ) {
        this.name = name;
        this.groupCategory = groupCategory;
    }

    public MyCategory(String name, String image, GroupCategory groupCategory) {
        this.name = name;
        this.image = image;
        this.groupCategory = groupCategory;
    }
}
