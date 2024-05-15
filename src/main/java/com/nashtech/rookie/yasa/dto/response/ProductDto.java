package com.nashtech.rookie.yasa.dto.response;

import com.nashtech.rookie.yasa.entity.Category;
import com.nashtech.rookie.yasa.entity.Rating;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDto {
    private int id;
    private String name;
    private int price;
    private String description;
    private String image;
    private Category category;
    private List<Rating> rating;
}
