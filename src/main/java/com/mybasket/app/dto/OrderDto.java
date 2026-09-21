package com.mybasket.app.dto;


import com.mybasket.app.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long id;
    private UserDto user;
    private OrderStatus orderStatus;
@Builder.Default
    private List<OrderItemDto> orderItems= new ArrayList<>();
}
