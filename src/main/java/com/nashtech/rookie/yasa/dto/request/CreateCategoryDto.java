package com.nashtech.rookie.yasa.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryDto {

    @NotBlank(message = "name is required")
    private String name;
    private String description;
}