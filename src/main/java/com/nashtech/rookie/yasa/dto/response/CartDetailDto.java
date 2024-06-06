package com.nashtech.rookie.yasa.dto.response;

import com.nashtech.rookie.yasa.entity.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDetailDto {
    //private Product product;
    private int productId;
    private Product product;
    private int quantity;
}
