package com.nashtech.rookie.yasa.dto.request;

import com.nashtech.rookie.yasa.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRatingDto {
    @NotNull(message = "Product is required")
    private Product product;
    private int userId;
    private float score;
    private String content;
}
