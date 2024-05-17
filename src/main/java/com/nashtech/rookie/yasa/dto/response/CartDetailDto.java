package com.nashtech.rookie.yasa.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDetailDto {
    //private Product product;
    private int productId;
    private int quantity;
}
