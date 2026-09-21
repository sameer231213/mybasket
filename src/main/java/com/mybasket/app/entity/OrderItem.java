package com.mybasket.app.entity;


import jakarta.persistence.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Entity
@Table(name="my-basket-order-items")
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Product product;


    private Integer quantity;

    @ManyToOne
    private Order order;
}
