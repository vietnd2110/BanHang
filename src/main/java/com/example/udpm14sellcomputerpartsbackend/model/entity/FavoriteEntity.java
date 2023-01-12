package com.example.udpm14sellcomputerpartsbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favorites")
@Data
public class FavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "like_date")
    @CreationTimestamp
    private LocalDateTime likeDate;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("user_id")
    private Long userId;


}
