package com.prod.ecommerce.order.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prod.ecommerce.user.model.User;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) 
@Table(name = "ecommerce_order") // Can't be order, otherwise will have sql syntax error
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ecommerce_order_id", nullable = false)
    private long ecommerceOrderId;
    @Column(nullable = false, length = 32)
    // Total price, delivery,...
    private String orderInfo;
    
    // 用Date类型，需要developer去写创建和修改时间
    // 而framework其实知道每个entity的修改时间
    // 加上@EntityListeners, JPA会自动在每个entity记录本地modify time。
    @LastModifiedDate
    private LocalDateTime updateTime;

    // Lazy:懒加载，查询需要的时候再用一个column，
    // 比如login，不需要manytoone relationship
    //
    // Merge: 当一个（父/子）表里entity改变的时候，如果关联的column改变了，
    // 在相关联的表里也会相应地自动改变
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ecommerce_user_id", referencedColumnName = "ecommerce_user_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private User user;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "order")
    private List<OrderGood> orderGoods;

    // Shipping info and payment not in scope
}
