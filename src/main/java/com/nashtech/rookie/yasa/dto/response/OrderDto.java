package com.nashtech.rookie.yasa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrderDto {
    private int id;
    private int total;
    private String name;
    private String address;
    private Set<OrderDetailDto> products;
}
