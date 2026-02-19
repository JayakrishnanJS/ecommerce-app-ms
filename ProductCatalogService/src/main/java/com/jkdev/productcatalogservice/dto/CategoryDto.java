package com.jkdev.productcatalogservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Long id;

    @NotNull // will be validated in controller layer(using @Valid), so we can return 400 Bad Request if name is missing
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String description;
}

