package com.prod.ecommerce.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prod.ecommerce.order.model.Order;
import com.prod.ecommerce.good.model.Good;

@Data
@Builder
@Entity
@Table(name = "ecommerce_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ecommerce_user_id", nullable = false)
    private long ecommerceUserId;
    @Column(nullable = false, unique = true, length = 64)
    private String name;
    @Column(name = "password", nullable = false, length = 64)
    private String password;
    @Column(nullable = false, length = 3)
    private int age;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = false, length = 1024)
    private String real_name;

    // ToString() method里，不要recursively print这个field
    // otherwise, stackoverflow
    @OneToMany(cascade =  CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Order> orders;
}
