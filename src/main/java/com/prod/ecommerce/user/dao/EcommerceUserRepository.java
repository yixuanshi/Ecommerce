package com.prod.ecommerce.user.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.prod.ecommerce.user.model.User;

import jakarta.transaction.Transactional;

public interface EcommerceUserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.name = :name and u.address = :address")
    User findUserByName(@Param("name") String name,
                        @Param("address") String address);

    @Query(value = "select * from User u where u.name = :aName", nativeQuery = true)
    User findUserByName(String aName);

    User findFirstByNameOrderByAgeAsc(String name);
    User findByName(String name);

    @Transactional
    @Modifying
    @Query("update User u set u.address = :address where u.name = :name")
    int updateUserByName(@Param("name") String name,
                         @Param("address") String address);
}
