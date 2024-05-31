package com.nashtech.rookie.yasa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "categories")
public class Category extends Audit {
    @Column(nullable = false)
    private String name;
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}