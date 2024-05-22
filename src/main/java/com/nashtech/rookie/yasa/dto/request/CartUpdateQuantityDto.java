package com.nashtech.rookie.yasa.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartUpdateQuantityDto {
    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "invalid quantity")
    private int quantity;
}
