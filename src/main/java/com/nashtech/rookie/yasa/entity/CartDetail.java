package com.nashtech.rookie.yasa.entity;

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
@Table(name = "cart_details")
public class CartDetail {

    @EmbeddedId
    private CartDetailKey id;

    @ManyToOne()
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne()
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public void addQuantity(int quantity) {
        this.quantity+=quantity;
    }
}
