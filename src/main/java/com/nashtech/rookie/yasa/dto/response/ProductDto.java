package com.nashtech.rookie.yasa.dto.response;

import com.nashtech.rookie.yasa.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private int id;
    private String name;
    private int price;
    private String description;
    private String image;
    private Category category;
}
