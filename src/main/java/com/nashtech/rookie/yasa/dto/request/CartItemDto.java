package com.nashtech.rookie.yasa.dto.request;
import com.nashtech.rookie.yasa.entity.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItemDto {
    //private Product product;
    private int productId;
    private int quantity;
}
