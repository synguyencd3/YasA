package com.nashtech.rookie.yasa.dto.response;

import com.nashtech.rookie.yasa.entity.Product;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {
    private int id;
    //private Product product;
    private int userId;
    private float score;
    private String content;
}
