package com.mybasket.app.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name="my_basket_product")
@Getter
@Setter
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long   productId;
    @NotBlank
    private String title;
    private String description;
    private String short_description;
    private double price;
    private boolean live;
    private boolean outofstock;
    private String imageUrl;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id")
    private FileMetaData image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


}
