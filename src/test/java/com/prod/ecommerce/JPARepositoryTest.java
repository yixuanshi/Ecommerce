package com.prod.ecommerce;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.prod.ecommerce.order.dao.EcommerceOrderRepository;
import com.prod.ecommerce.order.model.Order;
import com.prod.ecommerce.order.model.OrderGood;
import com.prod.ecommerce.user.dao.EcommerceUserRepository;
import com.prod.ecommerce.user.model.User;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.*;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
// @AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class JPARepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EcommerceUserRepository userRepository;

    @Autowired
    private EcommerceOrderRepository orderRepository;

    String test_user_name1;
    private User user1;

    @BeforeEach
    protected void setUp() {
        test_user_name1 = "test name1";
        user1 = User.builder()
                .name(test_user_name1).password("password1").age(20)
                .address("address1").real_name("real_name1")
                .build();
        System.out.println("Create a user:");
        System.out.println(user1);
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    // @Rollback(false)
    public void jpaUserKeywordTest() {
        System.out.println("Start jpaUserKeywordTest:");

        entityManager.persist(user1);

        User user2 = userRepository.save(user1);
        System.out.println("Pring create method output:");
        System.out.println(user2);
        assertThat(user2.getName()).isEqualTo(test_user_name1);

        User user3 = userRepository.findFirstByNameOrderByAgeAsc(test_user_name1);
        System.out.println("findFirstByNameOrderByAgeAsc:");
        System.out.println(user3);
        assertThat(user3.getName()).isEqualTo(test_user_name1);

        User user4 = userRepository.findUserByName(test_user_name1, "address1");
        System.out.println("Test @Query ");
        System.out.println(user4);
        assertThat(user4.getName()).isEqualTo(test_user_name1);

        int update_result = userRepository.updateUserByName(test_user_name1, "address_updated");
        System.out.println("Test @Query with update:");
        System.out.println(update_result);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    // @Rollback(false)
    public void jpaUserOrderRelationalTest() {
        System.out.println("Start jpaUserOrderRelationalTest:");
        String test_order_info = "test a order info";

        Order order1 = Order.builder()
                .orderInfo(test_order_info).build();
        System.out.println("Create a order:");
        System.out.println(order1);
        // NULL not allowed for column USER_ID
        // Order order5 = orderRepository.saveAndFlush(order1);
        // System.out.println("Print order5:");
        // System.out.println(order5);

        // User don't need List<Order> set to be inserted into database.
        // User user2 = userRepository.saveAndFlush(user1);
        // System.out.println("Print user2 output:");
        // System.out.println(user2);

        // Build relationship
        order1.setUser(user1);
        List<Order> user1_orders = new ArrayList<Order>();
        // List 至少要insert 2个elements用来test
        user1_orders.add(order1);
        user1.setOrders(user1_orders);

        User user2 = userRepository.save(user1);
        Order order2 = orderRepository.save(order1);
        System.out.println("Pring create method output:");
        System.out.println(order2.getOrderInfo());
        System.out.println(user1);

        assertThat(user2.getName()).isEqualTo(test_user_name1);
        assertThat(user2.getOrders().get(0).getOrderInfo()).isEqualTo(test_order_info);
        assertThat(order2.getOrderInfo()).isEqualTo(test_order_info);
        assertThat(order2.getUser().getName()).isEqualTo(test_user_name1);
    }
}
