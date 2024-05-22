package com.nashtech.rookie.yasa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDetailKey implements Serializable {
    @Column(name = "order_id")
    int orderId;
    @Column(name = "product_id")
    int productId;
}

