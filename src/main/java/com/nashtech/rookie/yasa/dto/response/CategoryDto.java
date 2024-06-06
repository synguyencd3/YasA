package com.nashtech.rookie.yasa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDto {
    private int id;
    private String name;
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
