package com.mybasket.app.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="my-basket-cart")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private int cartTotalItems;

    private Double totalAmount;


    @OneToOne( fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "cart" , fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();
}
