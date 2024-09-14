package com.prod.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prod.ecommerce.user.controller.UserController;
import com.prod.ecommerce.user.dao.EcommerceUserRepository;
import com.prod.ecommerce.user.service.UserService;
import com.prod.ecommerce.util.AjaxResponse;
import com.prod.ecommerce.user.model.User;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.*;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    @Resource
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void createUserTest() throws Exception {
        String user1_json = """
        {
            "name": "user 1",
            "password": "password 1",
            "age": "20",
            "address": "address 1",
            "real_name": "real name 1"
        }
        """;
        ObjectMapper objectMapper = new ObjectMapper();
        User userObj = objectMapper.readValue(user1_json, User.class);

        when(userService.userRegister(userObj)).thenReturn(AjaxResponse.success());

        // Throw request fail exception.
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .request(HttpMethod.POST, "/user/register")
                    .contentType("application/json")
                    .content(user1_json)
        ) .andExpect(MockMvcResultMatchers.content().json("{\"isok\":true,\"code\":200,\"message\":\"Request ok.\",\"data\":null}"))
        .andDo(print())
        .andReturn();

        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        log.info(mvcResult.getResponse().getContentAsString());
    }
}
