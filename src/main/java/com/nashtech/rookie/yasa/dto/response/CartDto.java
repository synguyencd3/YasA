package com.nashtech.rookie.yasa.dto.response;

import com.nashtech.rookie.yasa.entity.CartDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CartDto {
    private int total;
    private Set<CartDetail> products;
}
