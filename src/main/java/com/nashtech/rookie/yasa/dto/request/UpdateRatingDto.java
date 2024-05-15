package com.nashtech.rookie.yasa.dto.request;

import com.nashtech.rookie.yasa.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRatingDto {
    private Product product;
    private int userId;
    private float score;
    private String content;
}
