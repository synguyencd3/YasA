package com.nashtech.rookie.yasa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "ratings")
public class Rating extends Audit{
//    @Column(nullable = false)
//    private int productId;

    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonIgnore
    private Product product;
    @Column(nullable = false)
    private int userId;
    @Column(nullable = false)
    private float score;
    @Column(nullable = false)
    private String content;

}
