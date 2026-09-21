package com.mybasket.app.repository;

import com.mybasket.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface  ProductRepository extends JpaRepository<Product, Long> {


    //custom method/1. query methods
    //implementation of three methods are automatically done by framework.
    List<Product> findByTitle(String title);

    List<Product> findByLive(boolean live);

    List<Product> findByOutofstock(boolean outofstock);

    Optional <Product> findByProductIdAndTitle(Long productId, String title);


    //2.query methods

    //JPQL- Jakarta persistent query Language
    @Query("Select p from Product p")
    List<Product>getAllProduct();

    @Query("Select p from Product p where p.productId=?1 and p.price=?2")
    Product getProductByTitle(String title, double price);
}