package com.mybasket.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="mny_basket_category")
@Getter
@Setter
public class  Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String imageUrl;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new LinkedHashSet<>();
}