package com.prod.ecommerce.good.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prod.ecommerce.good.dao.GoodRepository;
import com.prod.ecommerce.good.model.Good;

import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.Resource;

@Slf4j
@Service
public class GoodServiceImpl implements GoodService {
    @Resource
    private GoodRepository goodRepository;
}
