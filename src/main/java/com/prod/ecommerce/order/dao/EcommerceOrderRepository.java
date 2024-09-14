package com.prod.ecommerce.order.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.prod.ecommerce.order.model.Order;

import jakarta.transaction.Transactional;

public interface EcommerceOrderRepository extends JpaRepository<Order,Long> {

    // @Query("select u from User u where u.name = :name and u.address = :address")
    // User findUserByName(@Param("name") String name,
    //                     @Param("address") String address);
}