package com.mybasket.app.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="my-basket-users")
@SQLDelete(sql="update `my-basket-users` SET deleted= true WHERE jpa_user_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
public class User extends BaseEntity {

    @Id
    @Column(name="jpa_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int userId;

    private String name;
    @Column(unique = true,length = 100)
    private String email;

    private String password;
    private String userImageUrl;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true )
    private Set<Address> addresses = new HashSet<>();


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<PaymentMethodInfo> paymentMethodInfos=new LinkedHashSet<>();




}
