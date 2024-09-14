package com.prod.ecommerce;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.prod.ecommerce.user.dao.EcommerceUserRepository;
import com.prod.ecommerce.order.dao.EcommerceOrderRepository;
import com.prod.ecommerce.user.model.User;
import com.prod.ecommerce.order.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;


@SpringBootTest
public class JPATest {
    // 需要mock database
    @Resource
    private EcommerceUserRepository userRepository;

    @Resource
    private EcommerceOrderRepository orderRepository;

    @BeforeEach
    protected void setUp() {
        
    }

    @AfterEach
    protected void tearDown() {

    }

    @Test
    public void jpaUserKeywordTest() {
        System.out.println("Start:");
        String test_name = "test a name";

        User user1 = User.builder()
            .name(test_name).password("password1").age(20)
            .address("address1").real_name("real_name1")
            .build();
        System.out.println("Create a user:");
        System.out.println(user1);

        User user2 = userRepository.save(user1);
        System.out.println("Pring create method output:");
        System.out.println(user2);

        User user3 = userRepository.findFirstByNameOrderByAgeAsc(test_name);
        System.out.println("findFirstByNameOrderByAgeAsc:");
        System.out.println(user3);

        User user4 = userRepository.findUserByName(test_name, "address1");
        System.out.println("Test @Query ");
        System.out.println(user4);

        int update_result = userRepository.updateUserByName(test_name, "address_updated");
        System.out.println("Test @Query with update:");
        System.out.println(update_result);
    }


    @Test
    // @Transactional
    public void jpaUserOrderRelationalTest() {
        System.out.println("Start:");
        String test_user_name = "test a user name";
        String test_order_name = "test a order name";

        Order order1 = Order.builder()
            .orderInfo(test_order_name).build();
        System.out.println("Create a order:");
        System.out.println(order1);

        User user1 = User.builder()
            .name(test_user_name).password("password1").age(20)
            .address("address1").real_name("real_name1")
            .build();
        System.out.println("Create a user:");
        System.out.println(user1);

        // User don't need order to be inserted into database.
        User user2 = userRepository.save(user1);
        System.out.println("Print user2 output:");
        System.out.println(user2);

        User user4 = userRepository.findUserByName(test_user_name, "address1");

        // Build relationship
        order1.setUser(user1);
        List<Order> user1_orders = new ArrayList<Order>();
        user1_orders.add(order1);
        user1.setOrders(user1_orders);

        userRepository.save(user1);
        Order order2 = orderRepository.save(order1);
        System.out.println("Pring create method output:");
        System.out.println(order2.getOrderInfo());
    }
}
