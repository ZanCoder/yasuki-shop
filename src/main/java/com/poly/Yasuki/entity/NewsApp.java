package com.poly.Yasuki.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "[news_app]")
public class NewsApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @Column(name = "create_at")
    @CreationTimestamp
    private Timestamp createAt;

    @Column(length = 4096)
    private String content;
    private String image;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    public NewsApp(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }
}