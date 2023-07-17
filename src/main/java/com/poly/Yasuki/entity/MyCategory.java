package com.poly.Yasuki.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "[Categories]")
public class MyCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String slug;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private GroupCategory groupCategory;
    public MyCategory( String name,GroupCategory groupCategory ) {
        this.name = name;
        this.groupCategory = groupCategory;
    }

}
