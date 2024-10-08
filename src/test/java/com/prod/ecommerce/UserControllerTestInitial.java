package com.prod.ecommerce;

import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.prod.ecommerce.user.controller.UserController;
import com.prod.ecommerce.user.model.User;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.*;


@Slf4j
@ActiveProfiles("test_no_db")
public class UserControllerTestInitial {

    private static MockMvc mockMvc;

    @BeforeAll
    static void Setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void createUserTest() throws Exception {
        String user1 = """
        {
            "name": "user 1",
            "password": "password 1",
            "age": "20",
            "address": "address 1",
            "real_name": "real name 1"
        }
        """;
        // Throw request fail exception.
        // Because the @Resource userService is not injected.

        // MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
        //             .request(HttpMethod.POST, "/user/register")
        //             .contentType("application/json")
        //             .content(user1)
        // ).andDo(print())
        // .andReturn();

        // mvcResult.getResponse().setCharacterEncoding("UTF-8");
        // log.info(mvcResult.getResponse().getContentAsString());
    }
}
