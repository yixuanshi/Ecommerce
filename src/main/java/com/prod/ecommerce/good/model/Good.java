package com.prod.ecommerce.good.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.None;
import com.prod.ecommerce.order.model.OrderGood;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ecommerce_good")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ecommerce_good_id", nullable = false)
    private long ecommerceGoodId;
    @Column(nullable = false, length = 32)
    private String goodName;
    @Column(nullable = false, length = 32)
    private String goodPrice;

    @Column(nullable = false, length = 32)
    private int goodQuantity;

    @LastModifiedDate
    private LocalDateTime updateTime;

    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "good")
    private List<OrderGood> orderGoods;
}
