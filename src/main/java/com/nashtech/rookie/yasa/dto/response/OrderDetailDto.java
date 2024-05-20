package com.nashtech.rookie.yasa.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailDto {
    private int productId;
    private int quantity;
}
