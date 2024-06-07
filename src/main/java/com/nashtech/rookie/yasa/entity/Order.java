package com.nashtech.rookie.yasa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends Audit{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int total;
    private String address;

    private String phone;

    @Column(name = "name")
    private String name;

    @Column(nullable = false)
    private String status;
    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> products = new HashSet<OrderDetail>();
}
