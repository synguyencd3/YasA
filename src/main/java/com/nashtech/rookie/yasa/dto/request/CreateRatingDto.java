package com.nashtech.rookie.yasa.dto.request;

import com.nashtech.rookie.yasa.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRatingDto {

    @NotBlank(message = "product is required")
    private Product product;
    @NotNull(message = "rating user is required")
    private int userId;
    private float score;
    private String content;
}
