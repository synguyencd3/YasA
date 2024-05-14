package com.nashtech.rookie.yasa.dto.request;

import com.nashtech.rookie.yasa.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateProductDto {
    @NotBlank(message = "Name is required")
    private String name;
    private int price;
    private String description;
    private String image;
    private Category category;

}
