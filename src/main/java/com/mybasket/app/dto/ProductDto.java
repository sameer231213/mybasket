package com.mybasket.app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class   ProductDto {


    private Long   productId;
    @NotBlank(message = "Product title is required")
    @Size(min=5, message = "Product title must be greater than 5 characters")
    private String title;
    @NotBlank(message = "Product des is required")
    private String description;
    @NotBlank(message = "Product desc is required")
    @Size(max=50, message = "Product short desc must be less than 50 characters")
    private String short_description;

    private double price;
    private boolean live;
    private boolean outofstock;
    private String imageUri;
}
