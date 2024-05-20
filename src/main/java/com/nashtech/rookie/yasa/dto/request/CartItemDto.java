package com.nashtech.rookie.yasa.dto.request;
import com.nashtech.rookie.yasa.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItemDto {
    @NotBlank(message = "Product is required")
    private int productId;

    @NotBlank(message = "Product is required")
    @Min(value = 1, message = "invalid quantity")
    private int quantity;
}
