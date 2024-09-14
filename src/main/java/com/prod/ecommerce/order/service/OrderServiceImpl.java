package com.prod.ecommerce.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prod.ecommerce.order.dao.EcommerceOrderRepository;
import com.prod.ecommerce.order.model.Order;

import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.Resource;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private EcommerceOrderRepository orderRepository;

    @Override
    public void getOrder(Order order) {
    }

}
