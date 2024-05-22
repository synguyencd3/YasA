package com.nashtech.rookie.yasa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetail {

    @EmbeddedId
    private OrderDetailKey id;

    @ManyToOne()
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne()
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

}
