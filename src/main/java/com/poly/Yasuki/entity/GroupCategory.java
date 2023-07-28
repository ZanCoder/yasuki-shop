package com.poly.Yasuki.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "[group_categories]")
public class GroupCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String slug;
    private Boolean isActive = false;

    @JsonIgnore
    @OneToMany(mappedBy = "groupCategory",  cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    Set<MyCategory> categories =  new HashSet<>();
}
