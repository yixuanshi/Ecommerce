package com.prod.ecommerce.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.prod.ecommerce.good.model.Good;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ecommerce_order_good")
public class OrderGood {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ecommerce_order_good_id", nullable = false)
    private long ecommerceOrderGoodId;
    @Column(nullable = false, length = 32)
    private String orderGoodName;
    @Column(nullable = false, length = 32)
    private String orderGoodPrice;

    @Column(nullable = false, length = 32)
    private int orderGoodQuantity;

    @LastModifiedDate
    private LocalDateTime updateTime;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ecommerce_good_id", referencedColumnName = "ecommerce_good_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Good good;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ecommerce_order_id", referencedColumnName = "ecommerce_order_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Order order;
}

