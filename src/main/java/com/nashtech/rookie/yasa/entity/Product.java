package com.nashtech.rookie.yasa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "products")
public class Product extends Audit{
    @Column(nullable = false)
    private String name;
    private int price;
    private String description;
    private String image;

    @Column(name="is_featured", nullable = false)
    private boolean isFeatured = false;

    @Column(name="average_rating")
    private float rating;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    Set<CartDetail> cart = new HashSet<CartDetail>();

}
