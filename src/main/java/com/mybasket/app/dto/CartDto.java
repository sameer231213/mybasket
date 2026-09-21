package com.mybasket.app.dto;

import java.util.List;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {

private Long id;
private int cartTotalItems;
private UserDto user;
    @Builder.Default
    private List<CartItemDto> items = new ArrayList<>();
}
