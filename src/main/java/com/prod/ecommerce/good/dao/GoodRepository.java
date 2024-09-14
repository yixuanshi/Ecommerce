package com.prod.ecommerce.good.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prod.ecommerce.good.model.Good;
import com.prod.ecommerce.order.model.Order;

import jakarta.annotation.Resource;


public interface GoodRepository extends JpaRepository<Good,Long> {
    
}

