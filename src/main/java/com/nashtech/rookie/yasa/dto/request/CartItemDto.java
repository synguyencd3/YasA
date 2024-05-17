package com.nashtech.rookie.yasa.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private int productId;
    private int quantity;
}
