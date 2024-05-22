package com.nashtech.rookie.yasa.dto.request;
import com.nashtech.rookie.yasa.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItemDto {
    @NotNull(message = "Product is required")
    private int productId;

    @NotNull(message = "Product is required")
    @Min(value = 1, message = "invalid quantity")
    private int quantity;
}
